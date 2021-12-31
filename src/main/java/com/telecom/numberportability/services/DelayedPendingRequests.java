package com.telecom.numberportability.services;
import com.telecom.numberportability.entity.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DelayedPendingRequests {

	@Autowired
	private RequestService requestService;

	// Reject Any pending Request that Exceeds 2 minutes
	@Scheduled(cron ="0 0/1 * * * ?")
	public void rejectDelayedRequests(){
		List<Request> pendingRequests = requestService.getAllPendingRequests();
		for(Request request : pendingRequests){
			Date timeNow = Calendar.getInstance().getTime();
			// Add 2 minutes to the Request Date to guarantee it stays at least 2 min without an action (pending)
			Date requestDate =addMinutesToDate(request.getRequestDate(),2);
			if(timeNow.after(requestDate)){
				request.setStatus("Rejected");
				requestService.addRequest(request);
			}
		}
	}

     // This method allows me to add 2 minutes To the requests
	private Date addMinutesToDate(Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}
}
