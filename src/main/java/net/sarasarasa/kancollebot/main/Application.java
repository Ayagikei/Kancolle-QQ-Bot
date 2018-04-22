package net.sarasarasa.kancollebot.main;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;


import com.scienjus.smartqq.model.*;
import com.scienjus.smartqq.callback.MessageCallback;
import com.scienjus.smartqq.client.SmartQQClient;
import net.sarasarasa.kancollebot.moduel.*;
import net.sarasarasa.kancollebot.tool.XMLResolver;

/**
 * @author AyagiKei
 * @url https://github.com/Ayagikei
 *
 **/

public class Application {

	private static XMLResolver xml;
	private static Rereader rereader;
	private static SmartQQClient client;


	public static void main(String[] args) {
		// 创建一个新对象时需要扫描二维码登录，并且传一个处理接收到消息的回调，如果你不需要接收消息，可以传null
		client = new SmartQQClient(new MessageCallback() {

			@Override
			public void onMessage(Message message) {



				if (message.getContent().contains("刷新群列表")) {
					client.getGroupList();
					client.sendMessageToFriend(message.getUserId(),xml.getByTag("flush"));
				} else if(message.getContent().contains("cosplay列表")){
					System.out.println("cosplay");
					client.sendMessageToFriend(message.getUserId(),xml.showCharacter());
				}
				else if(message.getContent().contains("cosplay")){
					String[] res = message.getContent().split("[^\\d]");

					for (String e : res) {

						// System.out.println(e);
						if (!e.equals(""))
							xml.changeCharacter(e);
						else continue;
					}

					client.sendMessageToFriend(message.getUserId(),"好的，提督！");
				}else if(message.getContent().contains("xml")){
					xml = new XMLResolver();
					client.sendMessageToFriend(message.getUserId(),"好的，提督！");
				}



			}

			@Override
			public void onGroupMessage(GroupMessage message) {

				//（不完全）避免自我调用
				if(String.valueOf(message.getUserId())== client.getAccountInfo().getUin())
				return;

				String msg = message.getContent().toLowerCase();

				String at = xml.getByTag("at");
				String at2 = xml.getByTag("at2");

				//复读
				if(!message.getContent().contains(at) && !message.getContent().contains(at2)){
					if(rereader.reread(msg))
						client.sendMessageToGroup(message.getGroupId(),message.getContent());
				}
				else if (message.getContent().contains(at) && msg.contains("roll")) {
					RollMachine rm = new RollMachine(client, message);
					rm.roll(message.getContent());
				} else if (message.getContent().contains(at) && msg.contains("官推")) {
					TwitterGetter twitterGetter = new TwitterGetter();
					client.sendMessageToGroup(message.getGroupId(), twitterGetter.getNewestTwitter());
				} else if (message.getContent().contains(at) && msg.contains("任务")) {
					QuestReminder qrer = new QuestReminder();
					client.sendMessageToGroup(message.getGroupId(), qrer.reminder());
				}  else if(message.getContent().contains(at) && msg.contains("cosplay列表")){
					client.sendMessageToGroup(message.getGroupId(),xml.showCharacter());
				}
				else if(message.getContent().contains(at) && msg.contains("cosplay")){
					String[] res = message.getContent().split("[^\\d]");

					for (String e : res) {

						// System.out.println(e);
						if (!e.equals(""))
							xml.changeCharacter(e);
						else continue;
					}

					client.sendMessageToGroup(message.getGroupId(),"好的，提督！");
				}
				else if ((message.getContent().contains(at2)) || message.getContent().contains(at)) {

					int num = (int) (Math.random() * 3 + 1);

					client.sendMessageToGroup(message.getGroupId(), xml.getCall(num));

				}

			}

			@Override
			public void onDiscussMessage(DiscussMessage message) {
				//接收讨论组信息
			}

		});

		// 登录成功后便可以编写你自己的业务逻辑了
		List<Category> categories = client.getFriendListWithCategory();
		for (Category category : categories) {
			System.out.println(category.getName());
			for (Friend friend : category.getFriends()) {
				System.out.println("————" + friend.getNickname());
			}
		}


		// 不获取登陆前的推特信息
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		// Calendar返回值0~11
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);
		TwitterGetter.setLastTime(year, month, date, hour, min, sec);

		TimeCounter tasks = new TimeCounter(client);
		try {
			tasks.go();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("———— 报时模块启动失败");
		}


		TwitterChecker twitterChecker = new TwitterChecker(client);
		Thread t2 = new Thread(twitterChecker);
		t2.start();
		System.out.println("———— 推特检测模块启动成功");

        xml = new XMLResolver();
        rereader = new Rereader();

		boolean needToClose = false;

		// 使用后调用close方法关闭，你也可以使用try-with-resource创建该对象并自动关闭
		if (needToClose)
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public static XMLResolver getXML(){
		return xml;
	}


}
