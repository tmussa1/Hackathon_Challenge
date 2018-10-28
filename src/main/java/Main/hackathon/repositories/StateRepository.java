package Main.hackathon.repositories;

import Main.hackathon.model.apis.State;
import org.springframework.data.repository.CrudRepository;

public interface StateRepository extends CrudRepository<State, Long> {
    State findByArea(String area);
}
