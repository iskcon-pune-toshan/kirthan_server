package org.iskon.services;

import org.iskon.models.Event;

import java.time.ZoneId;
import java.util.Date;
import java.util.Date;
import org.iskon.models.EventSearch;
import org.iskon.repositories.EventJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventServiceImpl implements EventService {

	@Autowired
	private EventJpaRepository eventJpaRepository;

	@Override
	public Event getEventById(int id){
		return eventJpaRepository.findById(id);
	}
	
	@Override
	public Event addEvent(Event event)
	{
		return eventJpaRepository.save(event);
	}

	@Override
	public Event updateEvent(Event event)
	{
		return eventJpaRepository.save(event);
	}

	@Override
	public void deleteEvent(Event event)
	{
		eventJpaRepository.delete(event);
	}

	@Override
	public List<Event> getEvents(EventSearch eventSearch)
	{
		return eventJpaRepository.findAll(new Specification<Event>(){

			@Override
			public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				ZoneId defaultZoneId = ZoneId.systemDefault();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
				if (eventSearch.getDateInterval() != null) {
					System.out.println("entered");
					LocalDate todaydate=LocalDate.now();
					String eventDate= dateTimeFormatter.format(todaydate);  
					
					LocalDate startDate = LocalDate.parse(eventDate);
					//Date date = Date.from(startDate.atStartOfDay(defaultZoneId).toInstant());
					int dayOffSet = 0;
					System.out.println(dayOffSet);
					if (eventSearch.getDateInterval().equals("TODAY")) {
						System.out.println("TODAY");
						dayOffSet = 1;
					}else if (eventSearch.getDateInterval().equals("TOMORROW")) {
						startDate=startDate.plusDays(1);
						System.out.println("TOMORROW");
						dayOffSet = 1;
					}
					else if (eventSearch.getDateInterval().equals("This Week")) {
						// This Week
						System.out.println("This Week");
						DayOfWeek dayOfWeek = startDate.getDayOfWeek();
						dayOffSet = DayOfWeek.SATURDAY.minus(dayOfWeek.getValue()).getValue();
					}
					else if (eventSearch.getDateInterval().equals("This Month")) {
						System.out.println("This Month");
						// This Month
						int dayOfMonth = startDate.getDayOfMonth();
						dayOffSet = startDate.lengthOfMonth() - dayOfMonth;
					}
					LocalDate endDate = startDate.plusDays(dayOffSet);
					String end = endDate.toString();
					//Date enddate = Date.from(endDate.atStartOfDay(defaultZoneId).toInstant());
					Date s=java.sql.Date.valueOf(startDate);
					Date e=java.sql.Date.valueOf(end);
					System.out.println(e);
				
					try {
						eventSearch.setEventEndDate(formatter.parse(endDate.format(dateTimeFormatter)));
						predicates.add(criteriaBuilder.between(root.get("eventDate"),s,e));
						//predicates.add(criteriaBuilder.between(root.get("eventDate"), t, e));
						
					}
					catch(Exception ex) {
						
					}
					
				}

				
				if(eventSearch.getEventStartDate() != null && eventSearch.getEventEndDate() != null)
					predicates.add(criteriaBuilder.between(root.get("eventDate"), eventSearch.getEventStartDate(), eventSearch.getEventEndDate()));
			
				if(eventSearch.getEventStartDate() != null)
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("eventDate"),eventSearch.getEventStartDate()));

				if(eventSearch.getEventEndDate() != null)
					predicates.add(criteriaBuilder.lessThan(root.get("eventDate"), eventSearch.getEventEndDate()));

				if(eventSearch.getEventDuration() != null)
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("eventDuration"), eventSearch.getEventDuration()));
				
				if(eventSearch.getEventLocation() != null)
					predicates.add(root.get("eventLocation").in(eventSearch.getEventLocation()));

				if (eventSearch.getEventType() != null)
					predicates.add(root.get("eventType").in(eventSearch.getEventType()));

				if(eventSearch.getLocality() != null)
					predicates.add(root.get("locality").in(eventSearch.getLocality()));

				if (eventSearch.getCity() != null) {
					LocalDate newDate=LocalDate.now();
					System.out.println(newDate);
					predicates.add(root.get("city").in(eventSearch.getCity()));}

				if (eventSearch.getPincode() != null)
					predicates.add(criteriaBuilder.equal(root.get("pincode"), eventSearch.getPincode()));

				if(eventSearch.getState() != null)
					predicates.add(root.get("state").in(eventSearch.getState()));

				if(eventSearch.getIsProcessed() != null)
					predicates.add(criteriaBuilder.equal(root.get("isProcessed") ,eventSearch.getIsProcessed()));

				if (eventSearch.getCreatedBy() != null)
					predicates.add(root.get("createdBy").in(eventSearch.getCreatedBy()));

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	@Override
	public Boolean processEvent(Event event)  {

		return eventJpaRepository.save(event) !=null;

	}
	
	@Override
	public List<Event> getEventTitle(EventSearch eventSearch)
	{
		return eventJpaRepository.findAll(new Specification<Event>(){

			@Override
			public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

	
				if(eventSearch.getEventStartDate() != null && eventSearch.getEventEndDate() != null)
					predicates.add(criteriaBuilder.between(root.get("eventDate"), eventSearch.getEventStartDate(), eventSearch.getEventEndDate()));

				if(eventSearch.getEventStartDate() != null)
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("eventDate"), eventSearch.getEventStartDate()));

				if(eventSearch.getEventEndDate() != null)
					predicates.add(criteriaBuilder.lessThan(root.get("eventDate"), eventSearch.getEventEndDate()));

				if(eventSearch.getEventDuration() != null)
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("eventDuration"), eventSearch.getEventDuration()));

				if(eventSearch.getEventLocation() != null)
					predicates.add(root.get("eventLocation").in(eventSearch.getEventLocation()));

				if (eventSearch.getEventType() != null)
					predicates.add(root.get("eventType").in(eventSearch.getEventType()));

				if(eventSearch.getLocality() != null)
					predicates.add(root.get("locality").in(eventSearch.getLocality()));

				if (eventSearch.getCity() != null)
					predicates.add(root.get("city").in(eventSearch.getCity()));

				if(eventSearch.getEventDate()!=null)
					predicates.add(criteriaBuilder.equal(root.get("eventDate"), eventSearch.getEventDate()));
				
				if (eventSearch.getPincode() != null)
					predicates.add(criteriaBuilder.equal(root.get("pincode"), eventSearch.getPincode()));

				if(eventSearch.getState() != null)
					predicates.add(root.get("state").in(eventSearch.getState()));

				if(eventSearch.getIsProcessed() != null)
					predicates.add(criteriaBuilder.equal(root.get("isProcessed") ,eventSearch.getIsProcessed()));

				if (eventSearch.getCreatedBy() != null)
					predicates.add(root.get("createdBy").in(eventSearch.getCreatedBy()));


				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}
}
