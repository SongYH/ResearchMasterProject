package Control;

import java.util.ArrayList;

import Common.Notice;

public class NoticeManager {
	private ArrayList<Notice> noticeList = null;
	private volatile static NoticeManager uniqueInstance = null;
	private NoticeManager()
	{
		noticeList = new ArrayList<Notice>();
	}
	public static NoticeManager getInstance()
	{
		if (uniqueInstance == null) {
			synchronized (NoticeManager.class) {
				if (uniqueInstance == null)
					uniqueInstance = new NoticeManager();
			}
		}
		return uniqueInstance;
	}

	public void add(Notice n)
	{
		noticeList.add(n);
	}
	public Notice getI(int i)
	{
		if(noticeList.size()>i && i >=0 )
			return noticeList.get(i);
		else return null;
	}
	public ArrayList<Notice> getList()
	{
		return noticeList;
	}
}
