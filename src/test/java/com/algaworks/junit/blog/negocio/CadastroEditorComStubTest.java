package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.exception.RegraNegocioException;
import com.algaworks.junit.blog.modelo.Editor;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CadastroEditorComStubTest {

    static CadastroEditor cadastroEditor;
    Editor editor;

    @BeforeAll
    static void beforeAll() {
        cadastroEditor = new CadastroEditor(
                new ArmazenamentoEditorFixoEmMemoria(),
                new GerenciadorEnvioEmail() {
                    @Override
                    void enviarEmail(Mensagem mensagem) {
                        System.out.println("Enviando mensagem para: " + mensagem.getDestinatario());
                    }
                }
        );
    }

    @BeforeEach
    void beforeEach() {
        editor = new Editor(null, "Cristiano", "cristiano.filho@email.com", BigDecimal.TEN, true);
    }

    @Test
    void Dado_um_editor_valido_Quando_criar_Entao_deve_criar_um_id_de_cadastro() {
        Editor editorSalvo = cadastroEditor.criar(editor);
        assertEquals(1L, editorSalvo.getId());
    }

    @Test
    void Dado_um_editor_null_Quando_criar_Entao_deve_lançar_exception() {
        assertThrows(NullPointerException.class, () -> cadastroEditor.criar(null));
    }

    @Test
    void Dado_um_editor_com_email_existente_Quando_criar_Entao_deve_lançar_exception() {
        editor.setEmail("cristiano.existe@email.com");
        assertThrows(RegraNegocioException.class, () -> cadastroEditor.criar(editor));
    }

    @Test
    void Dado_um_editor_com_email_existente_Quando_criar_Entao_nao_deve_salvar() {
        editor.setEmail("cristiano.existe@email.com");
        try {
            cadastroEditor.criar(editor);
        } catch (RegraNegocioException e) {}
    }
}