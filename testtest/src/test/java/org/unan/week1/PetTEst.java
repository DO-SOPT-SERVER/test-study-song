package org.unan.week1;

import org.unan.week1.fixture.PetFixture;

public class PetTEst {


    void test() {
        PetFixture.createPet();
        Pet pet = PetFixture.createInvalidPet();
    }

    void test2() {
        Pet pet = new Pet(2L, "강아지");
    }

    void test3() {
        Pet petFixture = createPet();
    }

    private Pet createPet() {
        return new Pet(3L, "사자");
    }

}
