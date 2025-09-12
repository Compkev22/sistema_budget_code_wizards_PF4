package org.code_wizards.Sistema_Budget.dominio.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface AiServiceBudget {
    @UserMessage("""
            Genera un saludo de bienvenida a la plataforma de Sistema_Budget.
            Usa menos de 120 caracteres y hazlo con un estilo semiformal.
            
            """)
    String generarSaludo();

    @SystemMessage("""
            Eres un experto en finanzas que recomienda que Tipo de Gastos personalizados segun los gustos del usuario.
            Debes recomendar maximo 3 Tipos de Gastos.
            No incluyas Tipos de Gastos fuera de la plataforma Sistema_Budget
            
            """)
    String generarSugerenciaTipoGassto(@UserMessage String mensaje);
}
