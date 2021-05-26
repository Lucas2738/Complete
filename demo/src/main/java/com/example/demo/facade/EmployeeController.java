package com.example.demo.facade;

import com.example.demo.business.DemoService;
import com.example.demo.business.domain.EmployeeModel;
import com.example.demo.business.domain.SecretDocumentRequest;
import com.example.demo.business.signature.Cert;
import com.example.demo.business.signature.CertificateHolder;
import com.example.demo.business.signature.CertificateService;
import com.example.demo.util.validations.BasicInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class EmployeeController {

	@Autowired
	DemoService service;

	@Autowired
	CertificateService demoSignature;

	@GetMapping("/users")
	public List<EmployeeModel> greeting() {
		return service.getAll();
	}

	@GetMapping("/user")
	public EmployeeModel greeting(@RequestParam String email) {
		return service.get(email);
	}
	
	@GetMapping("/userByRoleId")
	public List<EmployeeModel> greeting(@RequestParam Integer id) {
		return service.get(id);

	}
	
	@GetMapping("/user&CompanyByRoleId")
	public List<EmployeeModel> greeting3(@RequestParam Integer id) {
		return service.getEntityGraph(id);
	}
	
	@GetMapping("/userCompany&CountryByRoleId")
	public List<EmployeeModel> greeting4(@RequestParam Integer id) {
		return service.getEntitySubGraph(id);

	}

	@GetMapping("/getEmployee")
	public EmployeeModel greeting(@RequestParam int id) {
		return service.getQuerydsl(id);
	}
	
	@GetMapping("/getEmployeeByMailJpql")
	public EmployeeModel greeting2(@RequestParam String mail) {
		return service.getJPQL(mail);
	}

	@PostMapping(value = "/addEmployee", consumes = "application/json")
	public boolean addEmployee(@RequestBody @Validated(BasicInfo.class) EmployeeModel employee) {
		return service.addEmployee(employee);
	}

	@Cert
	@PostMapping(value = "/verifySignatureViaAOPAndJCA", consumes = "application/json")
	public boolean checkSign(@RequestBody @Validated(BasicInfo.class) SecretDocumentRequest employee) {
		return true;
	}

	@Cert(checkSignature = false, decryptContent = true)
	@PostMapping(value = "/sendEncrypetdData")
	String sendEncrypetdData(@RequestBody SecretDocumentRequest request) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidKeyException, JsonProcessingException {
		return demoSignature.encrypt(request.getDencryptedContent());
	}
}
