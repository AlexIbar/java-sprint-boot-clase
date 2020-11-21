package co.edu.iudigital.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.iudigital.app.exception.RecordNotFoundException;
import co.edu.iudigital.app.model.EmployeeEntity;
import co.edu.iudigital.app.repository.EmployeeRepository;

//Se anota service para que entre en el contenedor y que haga la inyección de dependencias

@Service
public class EmployeeService {

	// Inyeccción de dependencias
	@Autowired
	private EmployeeRepository employeeRepository;

	// Busca todos los empleados
	public List<EmployeeEntity> getAllEmployees() {
		List<EmployeeEntity> result = (List<EmployeeEntity>) employeeRepository.findAll();
		if (result.size() > 0) {
			return result;
		}
		return new ArrayList<EmployeeEntity>();
	}

	// Busca un empleado
	public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException {
		Optional<EmployeeEntity> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		}
		return new EmployeeEntity();
	}

	// Crea o actualiza un empleado
	public EmployeeEntity createOrOpdateEmployeeEntity(EmployeeEntity entity) {
		Long id = entity.getId();
		if (id == null) {
			entity = employeeRepository.save(entity);
			return entity;
		}
		Optional<EmployeeEntity> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			EmployeeEntity newEntity = employee.get();
			newEntity.setFirstName(entity.getFirstName());
			newEntity.setLastName(entity.getLastName());
			newEntity.setEmail(entity.getEmail());
			newEntity = employeeRepository.save(newEntity);

			return newEntity;
		} else {
			entity = employeeRepository.save(entity);
			return entity;
		}
	}
	
	//Borra un empleado por su ID
	public void deleteEmployeeById(Long id) throws RecordNotFoundException{
		Optional<EmployeeEntity> employe = employeeRepository.findById(id);
		if(employe.isPresent()) {
			employeeRepository.deleteById(id);
		}else {
			throw new RecordNotFoundException("No existe este empleado");
		}
	}
}
