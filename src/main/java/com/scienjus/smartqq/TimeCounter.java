package com.scienjus.smartqq;

import java.util.Date;

import com.scienjus.smartqq.client.SmartQQClient;

public class TimeCounter implements Runnable {

	private int hour;
	private SmartQQClient client;

	public TimeCounter(SmartQQClient client) {
		// TODO 自动生成的构造函数存根
		Date d = new Date();
		int hours = d.getHours();

		hour = hours;
		this.client = client;
	}

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(1000); // 睡眠2000后再次执行任务，模拟定时任务

				Date d = new Date();
				int hours = d.getHours();

				if (hours != hour) {
					hour = hours;

					switch (hours) {
					case 0:
						client.sendMessageToGroup(198921355, "哇哇！？正好0点啦！今天是我欧根亲王担任报时娘的角色呢。好，明白啦！交给我吧！");
						break;
					case 1:
						client.sendMessageToGroup(198921355, "凌晨1点到啦！以这样的元气的感觉来说吗？啊，是吗，太好啦！");
						break;
					case 2:
						client.sendMessageToGroup(198921355, "凌晨2点。这个国家称此时为“ushi…mitsu…doki”…吗？啊，总觉得这个短语的发音好有趣呢。");
						break;
					case 3:
						client.sendMessageToGroup(198921355, "哇，吓了我一跳。啊，很在意我这个发型吗？啊啊，已经凌晨3点啦！");
						break;
					case 4:
						client.sendMessageToGroup(198921355, "嗯嗯～～，凌晨4点了，嗯嗯～～不管怎么说是有些困了…但是，没关系的！");
						break;
					case 5:
						client.sendMessageToGroup(198921355, "5点到啦！早上好，好舒服的早晨呢！加油干吧！");
						break;
					case 6:
						client.sendMessageToGroup(198921355, "6点了呢。该吃早饭了啊…诶，我来做？明，明白了，交给我吧！嗯，做什么吃呢。俾斯麦姐姐的一份也要做吧，对吧♪～");
						break;
					case 7:
						client.sendMessageToGroup(198921355,
								"好，今天的早饭是面包。奶酪和香肠哦。还有沙拉和…当然，热乎乎的咖啡啦！\r\n" + "虽然普通但是很美味吧♪～啊，现在正好7点整。");
						break;
					case 8:
						client.sendMessageToGroup(198921355, "8点啦。来吧，Admiral先生，开始今天的作战吧！从演习开始？还是说远征？");
						break;
					case 9:
						client.sendMessageToGroup(198921355,
								"早上9点了。嗯？啊，是长门啊！喂♪～长～门♪～…嗯？在哪儿遇见的？那当然是～\r\n" + "…嗯…诶？在，在哪儿来着…");
						break;
					case 10:
						client.sendMessageToGroup(198921355, "10点了。战列舰？没问题！和俾斯麦姐姐在一起的话，完～全不用全力就能击沉呢！交给我吧♪～");
						break;
					case 11:
						client.sendMessageToGroup(198921355, "午前11点啦♪～差不多该吃午饭了呢。今天的话，在外面吃也挺好的呢。");
						break;
					case 12:
						client.sendMessageToGroup(198921355, "啊啊，已经中午了！正午了呢！午饭的话，在外面吃吃奶酪加面包也不错吧？心情好的话啤酒也来一发吗♪～啊，不行啊…");
						break;
					case 13:
						client.sendMessageToGroup(198921355, "13点了。午后作战开始了呢、加油干吧！");
						break;
					case 14:
						client.sendMessageToGroup(198921355, "14点了。诶，什么什么，酒匂？啊啊，我知道，那只可爱的轻巡吧。");
						break;
					case 15:
						client.sendMessageToGroup(198921355, "15点了。啊，这个吗？是的，这是“瑟布鲁斯”行动作战时的舰装。怎么样，合适我吧♪～");
						break;
					case 16:
						client.sendMessageToGroup(198921355, "16点了。哇，吓我一跳，是俾斯麦姐姐♪～我们一起出击吧！嗯，一定哦！");
						break;
					case 17:
						client.sendMessageToGroup(198921355, "17点了。到黄昏了呢。差不多，今天的太阳也要落下了呢。…哇，漂亮的天空…啊啊，当然俾斯麦姐姐才是最漂亮的♪～");
						break;
					case 18:
						client.sendMessageToGroup(198921355, "18点了。我去准备晚饭了。今天吃生冷食品怎么样？诶，不要？温热的食物比较好？");
						break;
					case 19:
						client.sendMessageToGroup(198921355,
								"19点了。那么，晚饭我试着做了德国猪肘子加浓汤。汤里透出的味道不错吧？\r\n" + "最后，在其中加入米饭如同杂烩一样非常美味哦♪～");
						break;
					case 20:
						client.sendMessageToGroup(198921355, "20点到了。日本的重巡们也过的非常的充实呢。嗯嗯，嗯～原来如此…嗯、嗯…");
						break;
					case 21:
						client.sendMessageToGroup(198921355,
								"21点了。诶，我有什么拿手好戏？嗯，让我想想，曾经，我用舰炮好好的教训了一群蜂拥而至的坦克。这个我出人意料的很在行呢！对！");
						break;
					case 22:
						client.sendMessageToGroup(198921355, "完全已经是晚上了呢，22点了。Admiral先生，今天一天的作♂战真是辛苦您了！");
						break;
					case 23:
						client.sendMessageToGroup(198921355, "23点了。嗯～～差不多我也要去休息了呢…晚安…诶，不行吗？");
						break;

					}
				}

				// System.out.println("时间：" + hours); // 执行任务
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
