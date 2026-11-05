package br.unisanta.apploginfirebase

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var loginButton: Button
    private lateinit var logoutButton: Button
    private lateinit var userCardView: MaterialCardView
    private lateinit var userNameText: TextView
    private lateinit var userEmailText: TextView
    private lateinit var titleText: TextView
    private lateinit var subtitleText: TextView
    private lateinit var iconImage: ImageView

    // Lançador do FirebaseUI
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Bindings
        loginButton = findViewById(R.id.loginButton)
        logoutButton = findViewById(R.id.logoutButton)
        userCardView = findViewById(R.id.userCardView)
        userNameText = findViewById(R.id.userNameText)
        userEmailText = findViewById(R.id.userEmailText)
        titleText = findViewById(R.id.titleText)
        subtitleText = findViewById(R.id.subtitleText)
        iconImage = findViewById(R.id.iconImage)

        // Botão de Login
        loginButton.setOnClickListener {
            createSignInIntent()
        }

        // Botão de Logout
        logoutButton.setOnClickListener {
            signOut()
        }

        // Atualiza a tela baseada no estado de auth ao iniciar
        updateUI()
    }

    private fun createSignInIntent() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        // Cria e lança o intent de login do FirebaseUI
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            // Aplica nosso tema customizado do FirebaseUITheme
            .setTheme(R.style.FirebaseUITheme)
            .build()

        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Sucesso no Login
            Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
            updateUI()
        } else {
            // Falha no Login (ou usuário cancelou)
            if (response != null) {
                Toast.makeText(this, "Falha no login: ${response.error?.errorCode}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                // Ao deslogar com sucesso, atualiza a tela
                updateUI()
                Toast.makeText(this, "Você saiu da conta.", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateUI() {
        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            // Usuário LOGADO
            loginButton.visibility = View.GONE

            userCardView.visibility = View.VISIBLE
            logoutButton.visibility = View.VISIBLE

            // Textos de boas-vindas mudam para algo mais pessoal
            titleText.text = "Olá!"
            subtitleText.text = "Seus dados de usuário"
            iconImage.setImageResource(android.R.drawable.ic_menu_myplaces)

            // Preenche dados do card
            userNameText.text = user.displayName ?: "Sem Nome"
            userEmailText.text = user.email ?: "Sem E-mail"
        } else {
            // Usuário DESLOGADO
            loginButton.visibility = View.VISIBLE

            userCardView.visibility = View.GONE
            logoutButton.visibility = View.GONE

            // Textos padrão
            titleText.text = "Bem-vindo!"
            subtitleText.text = "Faça login para continuar"
            iconImage.setImageResource(android.R.drawable.ic_dialog_info)
        }
    }
}
