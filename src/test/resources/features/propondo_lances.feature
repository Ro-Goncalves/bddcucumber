Feature: Propondo lances

    Scenario: Propondo um único lance válido
        Given Um lance válido
        When Propoe o lance
        Then O lance é aceito

    Scenario: Propondo vários lances válidos
        Given Vários lances válidos
        When Propoe vários lances
        Then Os lances são aceitos