package org.iskon.services;

import org.iskon.models.Event;
import org.iskon.models.EventSearch;
import org.iskon.repositories.EventJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
}
