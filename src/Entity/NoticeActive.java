package Entity;

import java.util.ArrayList;

import Common.Notice;

public class NoticeActive
{
	private ArrayList<Notice> noticeList = null;
	private volatile static NoticeActive uniqueInstance = null;

	private NoticeActive()
	{
		noticeList = new ArrayList<Notice>();
	}

	public static NoticeActive getInstance()
	{
		if (uniqueInstance == null)
		{
			synchronized (NoticeActive.class)
			{
				if (uniqueInstance == null)
					uniqueInstance = new NoticeActive();
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
		if (noticeList.size() > i && i >= 0)
			return noticeList.get(i);
		else
			return null;
	}

	public ArrayList<Notice> getList()
	{
		return noticeList;
	}

	public void setNoticeList(ArrayList<Notice> noticeList)
	{
		this.noticeList = noticeList;
	}
	
}