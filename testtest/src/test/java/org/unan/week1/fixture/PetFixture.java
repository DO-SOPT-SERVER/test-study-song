package org.unan.week1.fixture;

import org.unan.week1.Pet;

public class PetFixture {
    private static final Long ID = 1L;
    private static final Long INVALID_ID = 100L;
    private static final String NAME = "고양이";

    public static Pet createPet() {
        return new Pet(ID, NAME);
    }
    public static Pet createInvalidPet() {
        return new Pet(INVALID_ID, NAME);
    }

}
