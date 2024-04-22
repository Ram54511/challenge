/**
 * 
 */
package com.code.challenge.utils;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ram Thapa
 *
 */

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@CreationTimestamp
	private Date createdOn;

	@UpdateTimestamp
	private Date updatedOn;
}