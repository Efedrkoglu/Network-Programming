<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personel Kayit</title>
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
            <form action="/personelEkle" method="post">
                <table class="table">
                    <tr>
                        <td><label>Ad:</label></td>
                        <td><input type="text" name="name"></td>   
                    
                        <td><label>Soyad:</label></td>
                        <td><input type="text" name="surname"></td>
                    </tr>

                    <tr>    
                        <td><label>Sicil No:</label></td>
                        <td><input type="text" name="sicilNo"></td>

                        <td><label>Telefon:</label></td>
                        <td><input type="text" name="phone"></td>
                    </tr>
                    
                    <tr>
                        
                        <td><label>Departman:</label>
                        <td><select name="department">
                            <option value="Human Resources">Human Resources</option>
                            <option value="Marketing">Marketing</option>
                            <option value="Administration">Administration</option>
                            <option value="Finance">Finance</option>
                            <option value="Research and Development">Research and Development</option>
                            <option value="Customer Service">Customer Service</option>
                            <option value="Sales">Sales</option>
                            <option value="IT">IT</option>
                        </select></td>
                       
                        <td><label>İşe Giriş Tarihi:</label></td>
                        <td><input type="date" name="dateOfStart"></td>

                    </tr>

                    <tr>                        
                        <td><label>Maaş:</label></td>
                        <td><input type="number" name="salary"></td> 
                      
                        <td><label>Aktif:</label></td>
                        <td><input type="checkbox" name="isActive"></td> 
                    </tr>
                </table>
                <button type="submit" class="btn btn-primary btn-sm">Kaydet</button>
            </form>
            <p>${message}</p>
        </div>
        
        
    </body>
</html>
