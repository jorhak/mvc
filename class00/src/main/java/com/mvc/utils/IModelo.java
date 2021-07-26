/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.utils;

import java.util.List;
import java.util.Map;

/**
 *
 * @author jon
 */
public interface IModelo {
      public void setData(Map<String, String> request);

  public Map<String, String> save();

  public boolean delete(String id);

  public Map<String, String> find(String columnName, Object columnValue);

  public List<Map<String, String>> findAll();

  public Map<String, String> comboBox();
}
