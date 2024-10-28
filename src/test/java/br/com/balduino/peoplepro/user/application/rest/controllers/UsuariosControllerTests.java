package br.com.balduino.peoplepro.user.application.rest.controllers;

import br.com.balduino.peoplepro.user.core.application.rest.mapper.BusinessMapper;
import br.com.balduino.peoplepro.user.core.application.rest.request.CreateUsuarioEnderecoRequest;
import br.com.balduino.peoplepro.user.core.application.rest.request.CreateUsuarioRequest;
import br.com.balduino.peoplepro.user.core.application.rest.request.UpdateUsuarioEnderecoRequest;
import br.com.balduino.peoplepro.user.core.application.rest.request.UpdateUsuarioRequest;
import br.com.balduino.peoplepro.user.core.application.rest.response.CreateUsuarioResponse;
import br.com.balduino.peoplepro.user.core.application.rest.response.GetAllUsuariosResponse;
import br.com.balduino.peoplepro.user.core.application.rest.response.GetUsuarioByIdResponse;
import br.com.balduino.peoplepro.user.core.domain.model.Usuario;
import br.com.balduino.peoplepro.user.core.domain.model.UsuarioStatus;
import br.com.balduino.peoplepro.user.core.domain.model.vo.Endereco;
import br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository.UsuarioRepository;
import br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository.entity.UsuarioEntity;
import br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository.mapper.EntityMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Testcontainers
class UsuariosControllerTests {

    static final MySQLContainer MY_SQL_CONTAINER;

    static {
        MY_SQL_CONTAINER = new MySQLContainer<>(DockerImageName.parse("mysql:8.0"));
        MY_SQL_CONTAINER.start();
    }

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EntityMapper entityMapper;

    @Autowired
    private BusinessMapper businessMapper;

    private Usuario usuario;

    @DynamicPropertySource
    static void configureTestProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> MY_SQL_CONTAINER.getJdbcUrl());
        registry.add("spring.datasource.username", () -> MY_SQL_CONTAINER.getUsername());
        registry.add("spring.datasource.password", () -> MY_SQL_CONTAINER.getPassword());
        registry.add("spring.datasource.generate-ddl", () -> false);
    }

    @BeforeEach
    public void beforeEach() {
        usuario = Usuario.builder()
                .id(UUID.randomUUID())
                .cpf("31666236458")
                .nome("Teste")
                .dataNascimento(LocalDate.of(2000, 1, 1))
                .endereco(Endereco.builder()
                        .rua("Teste")
                        .numero("1")
                        .complemento("Teste")
                        .bairro("Teste")
                        .cidade("Teste")
                        .estado("Teste")
                        .cep("00000000")
                        .build())
                .status(UsuarioStatus.ATIVO)
                .build();

        usuarioRepository.saveAndFlush(entityMapper.toUsuarioEntity(usuario));
    }

    @AfterEach
    public void afterEach() {
        usuarioRepository.deleteAll();
    }

    @Test
    public void givenUsuario_whenGetUsuarioById_thenReturnUsuarioJson() throws Exception {
        webTestClient.get()
                .uri("/api/v1/usuarios/{usuarioId}", usuario.getId())
                .exchange()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(GetUsuarioByIdResponse.class)
                .consumeWith(response -> {
                    GetUsuarioByIdResponse usuarioResponse = response.getResponseBody();
                    Assertions.assertNotNull(usuarioResponse);
                    Assertions.assertEquals(usuarioResponse.getId(), this.usuario.getId());
                    Assertions.assertEquals(usuarioResponse.getCpf(), this.usuario.getCpf());
                    Assertions.assertEquals(usuarioResponse.getNome(), this.usuario.getNome());
                    Assertions.assertEquals(usuarioResponse.getDataNascimento().toLocalDate(), this.usuario.getDataNascimento());
                    Assertions.assertEquals(usuarioResponse.getStatus(), this.usuario.getStatus());
                    Assertions.assertNotNull(usuarioResponse.getEndereco());
                    Assertions.assertEquals(usuarioResponse.getEndereco().getRua(), this.usuario.getEndereco().getRua());
                    Assertions.assertEquals(usuarioResponse.getEndereco().getNumero(), this.usuario.getEndereco().getNumero());
                    Assertions.assertEquals(usuarioResponse.getEndereco().getComplemento(), this.usuario.getEndereco().getComplemento());
                    Assertions.assertEquals(usuarioResponse.getEndereco().getBairro(), this.usuario.getEndereco().getBairro());
                    Assertions.assertEquals(usuarioResponse.getEndereco().getCidade(), this.usuario.getEndereco().getCidade());
                    Assertions.assertEquals(usuarioResponse.getEndereco().getEstado(), this.usuario.getEndereco().getEstado());
                    Assertions.assertEquals(usuarioResponse.getEndereco().getCep(), this.usuario.getEndereco().getCep());
                });
    }

    @Test
    public void givenUsuario_whenGetAllUsuarios_thenReturnPageUsuarioJson() throws Exception {
        webTestClient.get()
                .uri("/api/v1/usuarios/")
                .exchange()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(GetAllUsuariosResponse.class)
                .consumeWith(response -> {
                    GetAllUsuariosResponse usuariosResponse = response.getResponseBody();
                    Assertions.assertNotNull(usuariosResponse);
                    Assertions.assertEquals(usuariosResponse.getContent().size(), 1);
                    Assertions.assertEquals(usuariosResponse.getTotalElements(), 1);
                    Assertions.assertEquals(usuariosResponse.getTotalPages(), 1);
                    Assertions.assertEquals(usuariosResponse.getNumber(), 0);
                    Assertions.assertEquals(usuariosResponse.getNumberOfElements(), 1);
                    Assertions.assertEquals(usuariosResponse.getSize(), 5);
                });
    }

    @Test
    public void givenNovoUsuario_whenCreateNovoUsuario_thenReturnNovoUsuarioJson() throws Exception {
        CreateUsuarioRequest createUsuarioRequest = CreateUsuarioRequest.builder()
                .cpf("432432236458")
                .nome("Teste 2")
                .dataNascimento(LocalDate.of(2024, 1, 1))
                .endereco(CreateUsuarioEnderecoRequest.builder()
                        .rua("Teste 2")
                        .numero("12")
                        .complemento("Teste 2")
                        .bairro("Teste 2")
                        .cidade("Teste 2")
                        .estado("Teste 2")
                        .cep("00000022")
                        .build())
                .status(UsuarioStatus.ATIVO)
                .build();

        webTestClient.post()
                .uri("/api/v1/usuarios/")
                .body(Mono.just(createUsuarioRequest), CreateUsuarioRequest.class)
                .exchange()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectStatus()
                .isCreated()
                .expectBody(CreateUsuarioResponse.class)
                .consumeWith(response -> {
                    CreateUsuarioResponse usuarioResponse = response.getResponseBody();
                    Assertions.assertNotNull(usuarioResponse);
                    Assertions.assertNotNull(usuarioResponse.getId());
                    Assertions.assertEquals(usuarioResponse.getCpf(), createUsuarioRequest.getCpf());
                    Assertions.assertEquals(usuarioResponse.getNome(), createUsuarioRequest.getNome());
                    Assertions.assertEquals(usuarioResponse.getDataNascimento().toLocalDate(), createUsuarioRequest.getDataNascimento());
                    Assertions.assertEquals(usuarioResponse.getStatus(), createUsuarioRequest.getStatus());
                    Assertions.assertNotNull(usuarioResponse.getEndereco());
                    Assertions.assertEquals(usuarioResponse.getEndereco().getRua(), createUsuarioRequest.getEndereco().getRua());
                    Assertions.assertEquals(usuarioResponse.getEndereco().getNumero(), createUsuarioRequest.getEndereco().getNumero());
                    Assertions.assertEquals(usuarioResponse.getEndereco().getComplemento(), createUsuarioRequest.getEndereco().getComplemento());
                    Assertions.assertEquals(usuarioResponse.getEndereco().getBairro(), createUsuarioRequest.getEndereco().getBairro());
                    Assertions.assertEquals(usuarioResponse.getEndereco().getCidade(), createUsuarioRequest.getEndereco().getCidade());
                    Assertions.assertEquals(usuarioResponse.getEndereco().getEstado(), createUsuarioRequest.getEndereco().getEstado());
                    Assertions.assertEquals(usuarioResponse.getEndereco().getCep(), createUsuarioRequest.getEndereco().getCep());
                });
    }

    @Test
    public void givenUsuarioExiste_whenUpdateUsuarioExistente_thenReturnUsuarioAtualizadoJson() throws Exception {
        UpdateUsuarioRequest updateUsuarioRequest = UpdateUsuarioRequest.builder()
                .cpf("432432236458")
                .nome("Teste 2")
                .dataNascimento(LocalDate.of(2024, 1, 1))
                .endereco(UpdateUsuarioEnderecoRequest.builder()
                        .rua("Teste 2")
                        .numero("12")
                        .complemento("Teste 2")
                        .bairro("Teste 2")
                        .cidade("Teste 2")
                        .estado("Teste 2")
                        .cep("00000022")
                        .build())
                .status(UsuarioStatus.ATIVO)
                .build();

        webTestClient.patch()
                .uri("/api/v1/usuarios/{usuarioId}", this.usuario.getId())
                .body(Mono.just(updateUsuarioRequest), CreateUsuarioRequest.class)
                .exchange()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectStatus()
                .isNoContent();

        UsuarioEntity usuarioDB = usuarioRepository.findById(this.usuario.getId()).orElseThrow();
        Assertions.assertNotNull(usuarioDB);
        Assertions.assertEquals(usuarioDB.getId(), this.usuario.getId());
        Assertions.assertEquals(usuarioDB.getCpf(), updateUsuarioRequest.getCpf());
        Assertions.assertEquals(usuarioDB.getNome(), updateUsuarioRequest.getNome());
        Assertions.assertEquals(usuarioDB.getDataNascimento().toLocalDate(), updateUsuarioRequest.getDataNascimento());
        Assertions.assertEquals(usuarioDB.getStatus(), updateUsuarioRequest.getStatus());
        Assertions.assertNotNull(usuarioDB.getEndereco());
        Assertions.assertEquals(usuarioDB.getEndereco().getRua(), updateUsuarioRequest.getEndereco().getRua());
        Assertions.assertEquals(usuarioDB.getEndereco().getNumero(), updateUsuarioRequest.getEndereco().getNumero());
        Assertions.assertEquals(usuarioDB.getEndereco().getComplemento(), updateUsuarioRequest.getEndereco().getComplemento());
        Assertions.assertEquals(usuarioDB.getEndereco().getBairro(), updateUsuarioRequest.getEndereco().getBairro());
        Assertions.assertEquals(usuarioDB.getEndereco().getCidade(), updateUsuarioRequest.getEndereco().getCidade());
        Assertions.assertEquals(usuarioDB.getEndereco().getEstado(), updateUsuarioRequest.getEndereco().getEstado());
        Assertions.assertEquals(usuarioDB.getEndereco().getCep(), updateUsuarioRequest.getEndereco().getCep());
    }

    @Test
    public void givenUsuarioExiste_whenDeleteUsuarioExistente_thenNotFoundDB() throws Exception {
        webTestClient.delete()
                .uri("/api/v1/usuarios/{usuarioId}", this.usuario.getId())
                .exchange()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectStatus()
                .isNoContent();
        Assertions.assertFalse(usuarioRepository.findById(this.usuario.getId()).isPresent());
    }
}
