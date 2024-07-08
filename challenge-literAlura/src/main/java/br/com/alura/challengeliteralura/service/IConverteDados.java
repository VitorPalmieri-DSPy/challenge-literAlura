package br.com.alura.challengeliteralura.service;

public interface IConverteDados {
   <T> T converterDados (String json, Class<T> classe);
}
