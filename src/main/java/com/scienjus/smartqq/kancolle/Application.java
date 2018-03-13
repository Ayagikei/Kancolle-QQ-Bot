package com.scienjus.smartqq.kancolle;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import com.scienjus.smartqq.callback.MessageCallback;
import com.scienjus.smartqq.client.SmartQQClient;
import com.scienjus.smartqq.model.Category;
import com.scienjus.smartqq.model.DiscussMessage;
import com.scienjus.smartqq.model.Friend;
import com.scienjus.smartqq.model.GroupMessage;
import com.scienjus.smartqq.model.Message;

/**
 * @author ScienJus
 * @date 2015/12/18.
 */
public class Application {

	public static void main(String[] args) {
		// 创建一个新对象时需要扫描二维码登录，并且传一个处理接收到消息的回调，如果你不需要接收消息，可以传null
		SmartQQClient client = new SmartQQClient(new MessageCallback() {
			@Override
			public void onMessage(Message message, SmartQQClient client) {
				System.out.println(message.getUserId());
				if (message.getContent().contains("刷新群列表")) {
					client.getGroupList();
				}
			}

			@Override
			public void onGroupMessage(GroupMessage message, SmartQQClient client) {

				String msg = message.getContent().toLowerCase();

				if (message.getContent().contains("欧根") && msg.contains("Roll")) {
					RollMachine rm = new RollMachine(client, message);
					rm.roll(message.getContent());
				} else if (message.getContent().contains("欧根") && message.getContent().contains("官推")) {
					TwitterGetter twitterGetter = new TwitterGetter();
					client.sendMessageToGroup(message.getGroupId(), twitterGetter.getNewestTwitter());
				} else if (message.getContent().contains("欧根") && message.getContent().contains("任务")) {
					QuestReminder qrer = new QuestReminder();
					client.sendMessageToGroup(message.getGroupId(), qrer.reminder());
				} else if ((message.getContent().contains("老婆")) || message.getContent().contains("欧根")) {
					System.out.println(message.getGroupId());
					int num = (int) (Math.random() * 3 + 1);
					System.out.println(num);
					switch (num) {
					case 1:
						client.sendMessageToGroup(message.getGroupId(), "Einen schönen Tag.");
						break;
					case 2:
						client.sendMessageToGroup(message.getGroupId(), "哇，吓了我一跳！…啊是！出击！");
						break;
					case 3:
						client.sendMessageToGroup(message.getGroupId(), "提督！莱茵演习吗！…啊什么啊不是啊…没关系，我会加油的！交给我吧！");
						break;
					default:
						break;
					}
				}

			}

			@Override
			public void onDiscussMessage(DiscussMessage message) {
				System.out.println(message.getContent());
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
		Thread t = new Thread(tasks);
		t.start();

		TwitterChecker twitterChecker = new TwitterChecker(client);
		Thread t2 = new Thread(twitterChecker);
		t2.start();

		boolean needToClose = false;

		// 使用后调用close方法关闭，你也可以使用try-with-resource创建该对象并自动关闭
		if (needToClose)
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
