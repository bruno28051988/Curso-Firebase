package com.br.cursofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    /*
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth autenticacaoUsuario = FirebaseAuth.getInstance();
     */

    private ImageView imageFoto;
    private Button buttonUpload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageFoto = findViewById(R.id.image_foto);
        buttonUpload = findViewById(R.id.button_upload);

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Configurando para imagem ser salva em memória
                imageFoto.setDrawingCacheEnabled(true);
                imageFoto.buildDrawingCache();

                //Recupera bitmap da imagem (imagem a ser carregada)
                Bitmap bitmap = imageFoto.getDrawingCache();

                //Comprimo bitmap para um formato png/jpeg
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos );

                //converte o baos para pixel brutos em uma matriz de bytes (dados imagem)
                byte[] dadosImagem = baos.toByteArray();

                //Define nós para o storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens");
                StorageReference imagemRef = imagens.child( "celular.jpeg");


                Glide.with(MainActivity.this)
                        .load(imagemRef)
                        .into(imageFoto);


                 /*imagemRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,
                                "Erro ao deletar imagem",
                                Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this,
                                "Sucesso ao deletar imagem",
                                Toast.LENGTH_SHORT).show();
                    }
                });/*/

                //nome da imagem
               //String nomeArquivo = UUID.randomUUID().toString();
               // StorageReference imagemRef = imagens.child( "celular.jpeg");

                //Retorna objeto que irá controlar o upload
                /*UploadTask uploadTask = imagemRef.putBytes(dadosImagem);
                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,
                                "Upload da imagem FALHOU: " + e.getMessage().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Uri url = task.getResult();
                                Toast.makeText(MainActivity.this,
                                        "Upload da imagem com Sucesso: ",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

            */
            }
        });



        //DatabaseReference usuarios = referencia.child("usuarios");

        //DatabaseReference usuarioPesquisa = usuarios.child("-Mai1CE1-2OhIxVvBYAv");
        //Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Marcelo");
        //Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(3);
        //Query usuarioPesquisa = usuarios.orderByKey().limitToLast(2);

        //Filtros - Maior ou Igual (>=)
        //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(35);

        //Filtros - Menor ou Igual (<=)
        //Query usuarioPesquisa = usuarios.orderByChild("idade").endAt(23);

        //Filtros - Entre dos valores
        //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(18).endAt(33);

       /*
        //Filtrar - Palavras
        Query usuarioPesquisa = usuarios.orderByChild("nome")
                .startAt("B")
                .endAt("R" + "\uf8ff");

        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Usuario dadosUsuario = snapshot.getValue(Usuario.class);
                //Log.i("Dados usuários ","nome: " + dadosUsuario.getNome() + "idade: " + dadosUsuario.getIdade());
                Log.i("Dados usuários: ", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        */

        /*
        Usuario usuario = new Usuario();
        usuario.setNome("Rubens");
        usuario.setSobrenome("Amaral");
        usuario.setIdade(25);
        usuario.setEndereco("Rua Corvo, 66 ");
        usuario.setTelefone("(41) xxxxx-xxxx");

        usuarios.push().setValue(usuario);

        */

        //deslogar usuários
        // autenticacaoUsuario.signOut();

        /*
        //logar usuario
        autenticacaoUsuario.signInWithEmailAndPassword(
                "bruno2@gmail.com", "bru1234")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.i("signIn", "Sucesso ao logar usuário");

                        }else{

                            Log.i("signIn", "Erro ao logar usuário");
                        }
                    }
                });

           */


        /*
        //Verifica autenticação do usuário logado
       if (autenticacaoUsuario.getCurrentUser() != null){
           Log.i("CreateUser", "Usuario logado");
       }else{
           Log.i("CreateUser", "Usuario não logado");
       }

      */

        /*
        //Cadatro de usuário
        autenticacaoUsuario.createUserWithEmailAndPassword(
                "bruno2@gmail.com", "bru1234")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.i("CreateUser", "Sucesso ao cadastrar usuário");

                        }else{

                            Log.i("CreateUser", "Erro ao cadastrar usuário");
                        }
                    }
                });
        */
         /*
        //referencia.child("usuarios2").child("001").child("nome").setValue("jamilton");
        DatabaseReference usuarios = referencia.child("usuarios");
        DatabaseReference produtos = referencia.child("produtos");

        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("FIREBASE", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Usuario usuario = new Usuario();
        usuario.setNome("Pedro");
        usuario.setSobrenome("Delara");
        usuario.setIdade(39);
        usuario.setEndereco("Rua João Negrão, 633");
        usuario.setTelefone("(41) 89787-8765");
        usuarios.child("002").setValue(usuario);

        Produto produto = new Produto();
        produto.setMarca("Iphone");
        produto.setDescricao("celular - iphone x ");
        produto.setPreco(3987.87);
        produtos.child("001").setValue(produto);
         */
    }

}