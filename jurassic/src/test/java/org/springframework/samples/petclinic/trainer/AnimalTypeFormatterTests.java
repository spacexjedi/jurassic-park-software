package org.springframework.samples.petclinic.trainer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/*
* @spacexjedi
*/

/*
* Test {@link AnimalTypeFormatter}
*/


@RunWith(MockitoJUnitRunner.class)
public class AnimalTypeFormatterTests {

	@Mock
	private AnimalRepository animals;

	private AnimalTypeFormatter animalTypeFormatter;

	@Before
	public void setup(){
		this.animalTypeFormatter = new AnimalTypeFormatter(animals);
	}

@Test
    public void testPrint() {
        AnimalType animalType = new AnimalType();
        animalType.setName("Chicken");
        String animalTypeName = this.animalTypeFormatter.print(animalType, Locale.ENGLISH);
        assertThat(animalTypeName).isEqualTo("Chicken");
    }

    @Test
    public void shouldParse() throws ParseException {
        given(this.animals.findAnimalTypes()).willReturn(makeAnimalTypes());
        AnimalType animalType = animalTypeFormatter.parse("Stegonosauros", Locale.ENGLISH);
        assertThat(animalType.getName()).isEqualTo("Stegonosauros");
    }

    @Test(expected = ParseException.class)
    public void shouldThrowParseException() throws ParseException {
        given(this.animals.findAnimalTypes()).willReturn(makeAnimalTypes());
        animalTypeFormatter.parse("Brontosauros", Locale.ENGLISH);
    }

    /**
     * 
     *
     * @return {@link Collection} of {@link AnimalType}
     */
    private List<AnimalType> makeAnimalTypes() {
        List<AnimalType> animalTypes = new ArrayList<>();
        animalTypes.add(new AnimalType() {
            {
                setName("Triceraptos");
            }
        });
        animalTypes.add(new AnimalType() {
            {
                setName("Stegonosauros");
            }
        });
        return animalTypes;
    }
}