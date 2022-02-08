package net.apiguides.springboot.entity;


public class FollowData {


	
	private String myUid;
	

	private String targetUid;

	public FollowData() {}
	

	public FollowData(String myUid, String targetUid) {
		super();
		this.myUid = myUid;
		this.targetUid = targetUid;
	}


	public String getMyUid() {
		return myUid;
	}


	public void setMyUid(String myUid) {
		this.myUid = myUid;
	}


	public String getTargetUid() {
		return targetUid;
	}


	public void setTargetUid(String targetUid) {
		this.targetUid = targetUid;
	}
	
	
	
}
