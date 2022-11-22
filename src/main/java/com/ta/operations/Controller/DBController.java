package com.ta.operations.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ta.operations.model.DBModel;
import com.ta.operations.repository.DBRepository;

@RestController
@RequestMapping("DB")
public class DBController {
	@Autowired
	DBRepository dbRepository;

	@GetMapping("/test1")
	public ResponseEntity<List<DBModel>> getAlltest(@RequestParam(required = false) String title) {
		List<DBModel> test = new ArrayList<DBModel>();
		System.out.println(test);
		try {

			if (title == null)
				dbRepository.findAll().forEach(test::add);
			else
				dbRepository.findByTitleContaining(title).forEach(test::add);

			if (test.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			System.out.println(test);
			return new ResponseEntity<>(test, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/test4/{id}")
	public ResponseEntity<DBModel> getDBModelById(@PathVariable("id") long id) {
		System.out.println(id);
		DBModel DBModel = dbRepository.findById(id);

		if (DBModel != null) {
			return new ResponseEntity<>(DBModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/test2")
	public ResponseEntity<String> createDBModel(@RequestBody DBModel DBModel) {
		try {
			dbRepository.save(new DBModel(DBModel.getTitle(), DBModel.getDescriptions(), false));
			return new ResponseEntity<>("DBModel was created successfully.", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/test5/{id}")
	public ResponseEntity<String> updateDBModel(@PathVariable("id") long id, @RequestBody DBModel DBModel) {
		DBModel _DBModel = dbRepository.findById(id);

		if (_DBModel != null) {
			_DBModel.setId(id);
			_DBModel.setTitle(DBModel.getTitle());
			_DBModel.setDescriptions(DBModel.getDescriptions());
			_DBModel.setPublished(DBModel.isPublished());

			dbRepository.update(_DBModel);
			return new ResponseEntity<>("DBModel was updated successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Cannot find DBModel with id=" + id, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/test6/{id}")
	public ResponseEntity<String> deleteDBModel(@PathVariable("id") long id) {
		try {
			int result = dbRepository.deleteById(id);
			if (result == 0) {
				return new ResponseEntity<>("Cannot find DBModel with id=" + id, HttpStatus.OK);
			}
			return new ResponseEntity<>("DBModel was deleted successfully.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cannot delete DBModel.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//@DeleteMapping("/test3")
	@RequestMapping(value = "/test3", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAlltest() {
		try {
			int numRows = dbRepository.deleteAll();
			return new ResponseEntity<>("Deleted " + numRows + " DBModel(s) successfully.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cannot delete test.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/test7/published")
	public ResponseEntity<List<DBModel>> findByPublished() {
		try {
			List<DBModel> test = dbRepository.findByPublished(true);

			if (test.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(test, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("print")
	public ResponseEntity<String> printmethod() {

		return new ResponseEntity<>("hoi", HttpStatus.OK);

	}

}