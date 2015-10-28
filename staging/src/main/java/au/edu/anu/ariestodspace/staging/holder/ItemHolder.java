package au.edu.anu.ariestodspace.staging.holder;

public class ItemHolder {
	private Integer itemId;
	private String handle;
	private String ariesIdentifier;
	private String title;
	
	public ItemHolder() {
		
	}
	
	public ItemHolder(Integer itemId, String handle, String ariesIdentifier, String title)
	{
		this.itemId = itemId;
		this.handle = handle;
		this.ariesIdentifier = ariesIdentifier;
		this.title = title;
	}
	
	public ItemHolder(Integer itemId, String handle, String ariesIdentifier) {
		this.itemId = itemId;
		this.handle = handle;
		this.ariesIdentifier = ariesIdentifier;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getAriesIdentifier() {
		return ariesIdentifier;
	}

	public void setAriesIdentifier(String ariesIdentifier) {
		this.ariesIdentifier = ariesIdentifier;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
