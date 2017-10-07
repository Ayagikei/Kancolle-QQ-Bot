package com.scienjus.smartqq;

import com.scienjus.smartqq.client.SmartQQClient;
import com.scienjus.smartqq.model.GroupMessage;

public class RollMachine {
	private SmartQQClient client;
	private GroupMessage msg;

	public RollMachine(SmartQQClient client, GroupMessage msg) {
		// TODO 自动生成的构造函数存根

		this.client = client;
		this.msg = msg;

	}

	private void printMessage(String meg) {
		System.out.println(meg);
		client.sendMessageToGroup(msg.getGroupId(), meg);
	}

	public void roll(String str) {
		// System.out.println(str);
		String[] res = str.split("[^\\d]");

		try {
			int goodNum = 0;

			for (String e : res) {

				// System.out.println(e);
				if (!e.equals(""))
					if (Integer.valueOf(e) > 100 || Integer.valueOf(e) <= 0) {
						printMessage("提督，你输入的数字有不在范围内的。（1~100）");
						return;
					} else {
						goodNum++;
					}

			}

			if (goodNum == 0)
				return;

		} catch (Exception e) {
			e.printStackTrace();
			printMessage("RollMachine出现异常");
			return;
		}

		try {
			boolean gotRes = false;

			int num;

			while (!gotRes) {

				StringBuffer s = new StringBuffer();

				for (int i = 0; i < 25; i++) {
					num = (int) (Math.random() * 100 + 1);
					s.append(num + " ");

					for (String e : res) {
						if (e.equals(String.valueOf(num)))
							gotRes = true;
					}

					if (gotRes)
						break;

				}

				printMessage(s.toString());

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("RollMachine出现异常");
		}

	}
}
