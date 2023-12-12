package rideTech.backend.Model;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Long id;
    
    @Column(name="category_name")
    public String name;

    @Column(name="category_Image")
    private String catimage;
    
    public Category() {
	}

public Category(String name) {
	super();
	this.name = name;
}

@Override
public String toString() {
	return "Category [id=" + id + ", name=" + name + ", catimage=" + catimage + "]";
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getCatimage() {
	return catimage;
}

public void setCatimage(String catimage) {
	this.catimage = catimage;
}



}
