package org.springframework.samples.petclinic.researcher;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
* @spacexjedi
*/

/**
 * Simple domain object representing a list of veterinarians. Mostly here to be used for the 'vets' {@link
 * org.springframework.web.servlet.view.xml.MarshallingView}.
 */

@XmlRootElement
public class Researchers {

	private List<Reseacher> researchers;

	@XmlElement
	public List<Reseacher> getReseacherList(){
		if (researchers == null) {
			researchers = new ArrayList<>();
		}

		return researchers;
	}
}