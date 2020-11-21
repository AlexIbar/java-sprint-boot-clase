package co.edu.iudigital.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.iudigital.app.model.EmployeeEntity;

@Repository //Para luego hacer inyeccción de dependencias

//Cuando quiero implementar una interfaz en otra no usamos implements, usamos extends como si se tratara de una clase
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long>{	

}
