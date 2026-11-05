package com.jatan.ems.repository;
import com.jatan.ems.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface EmployeeRepository extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {
 boolean existsByEmailIgnoreCase(String email);
 boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
 @Query("select count(e) from Employee e where e.status='ACTIVE'") long countActive();
 @Query("select count(e) from Employee e where lower(e.department)=lower(:department)")
 long countByDepartment(@Param("department") String department);
}
