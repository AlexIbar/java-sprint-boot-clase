package co.edu.iudigital.app.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.iudigital.app.model.EmployeeEntity;
import co.edu.iudigital.app.service.EmployeeService;

@Controller
@RequestMapping(value="/")
public class EmployeeController {
	// inyección de dependencias de service
		@Autowired
		private EmployeeService employeeService;
		
		// consultar todos los empleados
		// y muestra en la vista html
		@RequestMapping
		public String getAllEmployees(Model model) {
			List<EmployeeEntity> lista = employeeService.getAllEmployees();
			if(lista.size()== 0) {
				lista = null;
			}
			model.addAttribute("employees", lista);
			return "list-employees";
		}
}
