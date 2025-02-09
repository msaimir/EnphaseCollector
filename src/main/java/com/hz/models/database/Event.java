package com.hz.models.database;

import com.hz.metrics.Metric;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Event {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	@ToString.Exclude
	private List<Panel> panels = new ArrayList<>();
	private LocalDateTime time = LocalDateTime.now();
	private BigDecimal consumption = new BigDecimal(0);
	private BigDecimal production = new BigDecimal(0);
	private BigDecimal voltage = new BigDecimal(0);

	public void addSolarPanel(Metric metric) {
		if (metric.isSolarPanel()) {
			panels.add(new Panel(metric.getName(), metric.getValue()));
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Event event = (Event) o;
		return Objects.equals(id, event.id);
	}

	@Override
	public int hashCode() {
		return 0;
	}
}
