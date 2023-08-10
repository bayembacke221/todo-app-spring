package sn.bayembacke.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.bayembacke.todo.be.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
}
