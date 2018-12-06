/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication9;

/**
 *
 * @author Cuba
 */
src/main/java/com/example/restservice/RestserviceApplication.java

package com.example.restservice;


import java.util.ArrayList;

import java.util.List;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.util.UriComponentsBuilder;


@SpringBootApplication

public class RestserviceApplication {


    class Estado {


        private Long id;

        private String nome;


        private Estado(long id, String nome) {

            this.id = id;

            this.nome = nome;

        }


        private void setId(long id) {

            this.id = id;

        }


        private String getNome() {

            return nome;

        }


        private void setNome(String nome) {

            this.nome = nome;

        }


        private long getId() {

            return id;

        }

    }


    class Cidade {


        private Long id;

        private String nome;

        private Estado estado;


        

        private Cidade(long id, String nome,Estado estado) {

            this.id = id;

            this.nome = nome;

            this.estado = estado;

        }


        private void setId(long id) {

            this.id = id;

        }


        private String getNome() {

            return nome;

        }


        private void setNome(String nome) {

            this.nome = nome;

        }


        private long getId() {

            return id;

        }


        private Estado getEstado() {

            return estado;

        }


        private void setEstado(Estado estado) {

            this.estado = estado;

        }

        

        

    }


    class DAOEstado {


        private final List<Estado> estados = new ArrayList<>();


        Estado est1 = new Estado(1, "North Carolina");

        Estado est2 = new Estado(2, "South Carolina");


        private List<Estado> getAllEstados() {

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            return estados;

        }


        private Estado addEstado(Estado estado) {

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            Random random = new Random();

            int nextId = random.nextInt(1000) + 10;


            estado.setId(nextId);

            estados.add(estado);


            return estado;

        }


        private void updateEstado(Estado estado) {

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            for (Estado oldEstado : estados) {

                if (oldEstado.getId() == estado.getId()) {

                    oldEstado.setNome(estado.getNome());

                }

            }

        }


        private void delete(long id) {

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            for (Estado estado : estados) {

                if (estado.getId() == id) {

                    // TO DO;

                    break;

                }

            }

        }


    }

    class DAOCidade {


        private final List<Cidade> cidades = new ArrayList<>();


        Cidade c1;


        DAOCidade(Estado estado,Cidade cidade) {

            this.c1 = new Cidade(0, "Cameron", estado);

        }

        


        public List<Cidade> getAllCidades() {

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            return cidades;

        }


        public Cidade addCidade(Cidade cidade) {

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            Random random = new Random();

            int nextId = random.nextInt(1000) + 10;


            cidade.setId(nextId);

            cidades.add(cidade);


            return cidade;

        }


        public void updateCidade(Cidade cidade) {

            //throw new UnsupportedOperationException("Not supported yet."); //To
QUI, 11:19
Certificado Iniciaão Científica.pdf
22:16
package com.example;



import java.util.*;

import com.microsoft.azure.functions.annotation.*;

import com.microsoft.azure.functions.*;

import java.util.stream.Collectors;

import java.util.stream.Stream;

import lombok.Data;


public class Function {


    DAO cidadeDao = new DAO();


    @FunctionName("criarCidade")

    public Cidade create(

            @HttpTrigger(name = "createcidade",

                    methods = {HttpMethod.POST}, route = "cidade") Cidade c) {

        //TO DO

        return cidadeDao.create(c);


    }


    @FunctionName("readCidade")

    public List<Cidade> read(

            @HttpTrigger(name = "readcidade",

                    methods = {HttpMethod.GET}, route = "cidade") String cidade) {


        return cidadeDao.read();


    }


    @FunctionName("updateCidade")

    public Cidade update(

            @HttpTrigger(name = "updatecidade", methods = {HttpMethod.PUT}, route = "cidade") Cidade c) {

        //TO DO

        return cidadeDao.update(c);

    }


    @FunctionName("deleteCidade") // DELETE,DELETE,OBSOLETE

    public int delete(

            @HttpTrigger(name = "deletecidade", methods = {HttpMethod.DELETE}, route = "cidade/{id}")

            @BindingName("id") Long id) {

        //TO DO

        return 200;

    }

}


class DAO {


    public Cidade create(Cidade c) {

        Cidade cidade = new Cidade();

        return c;

    }


    public List<Cidade> read() {

        return Stream.of(

                new Cidade(1L, "Apucarana", new Estado(1L, "PR")),

                new Cidade(2L, "Arapongas", new Estado(1L, "PR"))

        ).collect(Collectors.toList());

    }


    public Cidade update(Cidade c) {

        c.setId(c.getId() + 1L);

        c.setNome(c.getNome());

        return c;

    }


    public int delete(Long id) {

        return 200;

    }


}


@Data

class Cidade {


    private Long id;

    private String nome;

    private Estado estado;


    public Cidade(Long id, String nome, Estado estado) {

        this.setId(id);

        this.setNome(nome);

        this.setEstado(estado);

    }

}


@Data

class Estado {


    private Long id;

    private String nome;


    public Estado(Long id, String nome) {

        this.setId(id);

        this.setNome(nome);

    }

}
