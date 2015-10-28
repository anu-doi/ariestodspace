package au.edu.anu.ariestodspace.staging.sword.data;

import java.util.List;

/**
 * 
 * @author Rahul Khanna
 *
 */
public interface SwordRequestDataProvider {

	public List<SwordRequestData> getSwordRequests();
	
	public void updateRequestStatus(SwordRequestData data, boolean isSuccess);
}
