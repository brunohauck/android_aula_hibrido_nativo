package com.example.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;

    final Fragment aboutFragment = new AboutFragment();
    final Fragment productListFragment = new ProductListFragment();
    final Fragment webViewFragment = new WebViewFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = productListFragment;


    // Primeiramente ele cria um switch case para tratar os eventos do menu
    // BottomNavigation
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //neste momento definimos qual fragmente será carregado no caso
                    //seria productList e com o comando hide escondemos o fragment que 	    //está sendo mostrado na tela
                    fm.beginTransaction().hide(active).show(productListFragment).commit();
                    active = productListFragment;
                    return true;
                case R.id.navigation_dashboard:
                    fm.beginTransaction().hide(active).show(webViewFragment).commit();
                    active = webViewFragment;
                    return true;
                case R.id.navigation_notifications:
                    fm.beginTransaction().hide(active).show(aboutFragment).commit();
                    active = aboutFragment;
                    return true;
            }
            return false;
        }
    };
    // OnCreate é a primeira função a ser executada no ciclo de vida de uma Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // infla o layout XML
        setContentView(R.layout.activity_main);
        // infla o layout de um text view e atribui ele a uma variável Textview
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //podemos alterar o valor de textView conforme clicamos nos botões
        // Neste momento definimos todos os fragments e qual será o principal os outros         //ficarão escondidios
        fm.beginTransaction().add(R.id.frameLayout, aboutFragment, "3").hide(aboutFragment).commit();
        fm.beginTransaction().add(R.id.frameLayout, webViewFragment, "2").hide(webViewFragment).commit();
        fm.beginTransaction().add(R.id.frameLayout, productListFragment, "1").commit();
    }

}

