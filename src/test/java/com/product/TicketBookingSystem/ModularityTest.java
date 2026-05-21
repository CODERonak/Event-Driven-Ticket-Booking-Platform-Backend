package com.product.TicketBookingSystem;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModularityTest {

    ApplicationModules modules = ApplicationModules.of(TicketBookingSystemApplication.class);

    @Test
    void verifyModularStructure() {
        modules.verify();
    }

    @Test
    void documentModules() {
        new Documenter(modules)
                .writeDocumentation()
                .writeIndividualModulesAsPlantUml();
    }
}
