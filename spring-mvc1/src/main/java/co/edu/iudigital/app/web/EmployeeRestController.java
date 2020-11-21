package co.edu.iudigital.app.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.iudigital.app.exception.RecordNotFoundException;
import co.edu.iudigital.app.model.EmployeeEntity;
import co.edu.iudigital.app.service.EmployeeService;

@RestController
@RequestMapping(value = "/rest") // endpoint
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })

public class EmployeeRestController {

	// Inyección de dependencias
	@Autowired
	private EmployeeService employeeService;

	// Punto de petición get

	@RequestMapping(value = { "" }, method = RequestMethod.GET)

	public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
		return new ResponseEntity<List<EmployeeEntity>>(employeeService.getAllEmployees(), HttpStatus.OK);
	}

	// Punto de petición get para listar empleado por el id

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<EmployeeEntity> get(@PathVariable("id") Long id) throws RecordNotFoundException {
		EmployeeEntity employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	// punto de petición POST para guardar o actualizar un empleado
	@RequestMapping(path = "/createorupdate", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<EmployeeEntity> createOrUpdate(@RequestBody EmployeeEntity employee) {
		EmployeeEntity emp = employeeService.createOrOpdateEmployeeEntity(employee);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
	
	// punto de petición DELETE para borrar un empleado por su ID
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<List<EmployeeEntity>> 
			delete(@PathVariable("id") Long id) throws RecordNotFoundException{
			employeeService.deleteEmployeeById(id);
			List<EmployeeEntity> lista = employeeService.getAllEmployees();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		}

}
