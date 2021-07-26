/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jon
 */
public interface IControlador {

    public void create(HttpServletRequest req, HttpServletResponse res);

    public void save(HttpServletRequest req, HttpServletResponse res);

    public void delete(HttpServletRequest req, HttpServletResponse res);

    public void find(HttpServletRequest req, HttpServletResponse res);

    public void findAll(HttpServletRequest req, HttpServletResponse res);
}
