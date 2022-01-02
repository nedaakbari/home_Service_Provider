package ir.maktab.homeServiceProvider.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@ToString(callSuper = true)
public class Customer extends User {

}
