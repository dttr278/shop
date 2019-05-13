/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import model.Product;

/**
 *
 * @author DO TAN TRUNG
 */
@WebServlet(name = "UpdateProduct", urlPatterns = {"/UpdateProduct"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UpdateProduct extends HttpServlet {

    public static final String SAVE_DIRECTORY = "img/products";

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ProductDao dao = new ProductDao();
            int id = Integer.valueOf(request.getParameter("id"));
            Product product;
            if (id > 0) {
                product = dao.getProduct(id);
            } else {
                product = new Product();
            }
            String name = request.getParameter("name");
            int type = Integer.valueOf(request.getParameter("type"));
            int amount = Integer.valueOf(request.getParameter("amount"));
            double value = Double.valueOf(request.getParameter("value"));
            float promotionValue = Float.valueOf(request.getParameter("promotionValue"));
            String detail = request.getParameter("detail");
            detail=new String(detail.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            String promotion=request.getParameter("promotion");
            String fileName = uploadImg(request);
            if (fileName != null) {
                 product.setImage(fileName);
            }

            product.setDetail(detail);
            product.setId_type(type);
            product.setStatus(0);
            product.setNew_product(1);
            product.setName(name);
            product.setValue(value);
            product.setPromotion_price(promotionValue);
            product.setPromotion(promotion);
            product.setAmount(amount);
            if (id > 0) {
                dao.updateProduct(product);
            } else {
                id=dao.insertProductGetId(product);
                if(id<1){
                    response.getWriter().println("insert error");
                }
            }
            
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/productdetail.jsp?productId="+id);
//            dispatcher.forward(request, response);
              response.sendRedirect("productdetail.jsp?productId="+id);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    private String uploadImg(HttpServletRequest request) throws IOException, ServletException {
        // Đường dẫn tuyệt đối tới thư mục gốc của web app.
        String appPath = request.getServletContext().getRealPath("");
        appPath = appPath.replace('\\', '/');

        // Thư mục để save file tải lên.
        String fullSavePath = null;
        if (appPath.endsWith("/")) {
            fullSavePath = appPath + SAVE_DIRECTORY;
        } else {
            fullSavePath = appPath + "/" + SAVE_DIRECTORY;
        }

        // Tạo thư mục nếu nó không tồn tại.
        File fileSaveDir = new File(fullSavePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        String fName = null;
        String fileName = null;
        // Danh mục các phần đã upload lên (Có thể là nhiều file).
        for (Part part : request.getParts()) {
            fileName = extractFileName(part);
            if (fileName != null && fileName.length() > 0) {
                fName = fileName;
                String filePath = fullSavePath + File.separator + fileName;
                System.out.println("Write attachment to file: " + filePath);

                // Ghi vào file.
                part.write(filePath);
            }
        }
        return fName;
    }

    private String extractFileName(Part part) {
        // form-data; name="file"; filename="C:\file1.zip"
        // form-data; name="file"; filename="C:\Note\file2.zip"
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                // C:\file1.zip
                // C:\Note\file2.zip
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                // file1.zip
                // file2.zip
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
