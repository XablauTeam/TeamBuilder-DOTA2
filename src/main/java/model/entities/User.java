package model.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer idUsuario;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "login", unique = true)
	private String login;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity=DotaPlayer.class)
	private DotaPlayer player;

	public User() {
		super();
	}

	public User(String nome, String login, String password, String email) {
		super();
		this.nome = nome;
		this.login = login;
		this.password = password;
		this.email = email;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DotaPlayer getPlayer() {
		return player;
	}

	public void setPlayer(DotaPlayer player) {
		this.player = player;
	}

}
