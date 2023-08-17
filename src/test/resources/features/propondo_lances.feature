Feature: Propondo lances

    Scenario: Propondo um único lance válido
        Given Um lance válido
        When Propoe o lance
        Then O lance é aceito

    Scenario: Propondo vários lances válidos
        Given Um lance de 10.00 reais do usuário "Fulano"
        And Um lance de 15.00 reais do usuário "Beltrano"
        When Propoe vários lances
        Then Os lances são aceitos

    Scenario Outline: Propondo um <valor> de lance inválido
        Given Um lance inválido de <valor> reais
        When Propoe o lance
        Then O lance não é aceito

        Examples: 
            | valor |
            | 0     |
            | -1    |

    Scenario: Propondo uma sequência de lances
        Given dois lances
            | valorLance | nomeUsuario |
            | 10         | fulano      |
            | 15         | fulano      |        
        When Propoe vários lances
        Then O segundo lance não é aceito