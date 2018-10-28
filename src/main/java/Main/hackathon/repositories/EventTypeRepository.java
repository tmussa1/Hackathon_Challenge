package Main.hackathon.repositories;

import Main.hackathon.model.apis.EventTypeApi.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventTypeRepository extends CrudRepository<Event,Long> {

}
