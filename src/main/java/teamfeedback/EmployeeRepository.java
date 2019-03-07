package teamfeedback;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<DefaultEmployee, Long> {

    List<DefaultEmployee> findByLastName(String lastName);
}