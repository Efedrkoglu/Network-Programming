<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personel Listesi</title>
        <link rel="stylesheet" href="resources/css/bootstrap.css">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.html">Anasayfa</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="navbar-nav">
                        <a class="nav-link" href="personelEkle.jsp">Personel Kayit</a>
                        <a class="nav-link" href="personelListele">Personel Listesi</a>
                    </div>
                </div>
            </div>
        </nav>
        <div class="container mt-4">
            <table class="table table-striped table-hover">
                <tr>
                    <th>Ad</th>
                    <th>Soyad</th>
                    <th>Sicil No</th>
                    <th>Departman</th>
                    <th>Tel No</th>
                    <th>Başlama Tarihi</th>
                    <th>Maaş</th>
                    <th>Aktiflik</th>
                </tr>
                <c:forEach items="${personelList}" var="p" varStatus="status">
                    <tr>
                        <td>${p.name}</td>
                        <td>${p.surname}</td>
                        <td>${p.sicilNo}</td>
                        <td>${p.department}</td>
                        <td>${p.phone}</td>
                        <td>${p.dateOfStart}</td>
                        <td>${p.salary}</td>
                        <td>${p.isActive}</td>
                    </tr>
                </c:forEach>
            </table>
            <hr/>
            <p>Toplam Personel: ${personelCount}</p>
            <p>Personele ödenen toplam maaş: ${totalSalary}</p>
        </div>
    </body>
</html>
