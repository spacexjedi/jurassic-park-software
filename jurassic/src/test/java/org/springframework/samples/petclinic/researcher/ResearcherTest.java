/**
 * @spacexjedi
 *
 */
public class VetTests {

    @Test
    public void testSerialization() {
        Researcher res = new Researcher();
        res.setFirstName("Alan");
        res.setLastName("Grant");
        res.setId(123);
        Researcher other = (Researcher) SerializationUtils
                .deserialize(SerializationUtils.serialize(res));
        assertThat(other.getFirstName()).isEqualTo(res.getFirstName());
        assertThat(other.getLastName()).isEqualTo(res.getLastName());
        assertThat(other.getId()).isEqualTo(res.getId());
    }

}
