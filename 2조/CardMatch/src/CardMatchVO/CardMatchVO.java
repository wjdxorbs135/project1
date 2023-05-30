package CardMatchVO;

public class CardMatchVO {
	
	private String name;
	private int minutes;
	private int second;
	
	public CardMatchVO() {}
	
	public CardMatchVO(String name,int minutes,int second) {
		
		this.name = name;
		this.minutes = minutes;
		this.second = second;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}
}