package fr.cs.bazarshop.repository;

import fr.cs.bazarshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
