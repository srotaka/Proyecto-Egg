package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.exeptions.ConflictException;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.exeptions.NotFoundException;
import grupo7.egg.nutrividas.repositorios.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class FotoServicio {

    @Autowired
    private FotoRepository fotoRepositorio;

    @Autowired
    private Foto foto;

    public final Boolean DISCHARGE = Boolean.TRUE;

    @Transactional
    public Foto crearFoto(String folderLocation, String id, String name, MultipartFile multipartFile){

        if (multipartFile.isEmpty()) {
            throw new FieldInvalidException("La imagen no puede ser nula");
        }

        String nameFormated = name.replaceAll("\\s","-");
        String finalPath = createPath(multipartFile,folderLocation,id,nameFormated);
        String relativPath =finalPath.substring(25);
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(finalPath);
            Files.write(path, bytes);

            foto.setMime(multipartFile.getContentType());
            foto.setNombre(multipartFile.getName());
            foto.setRuta(relativPath);
            foto.setAlta(DISCHARGE);
            return fotoRepositorio.save(foto);
        }catch (Exception e) {
            e.printStackTrace();
            throw new ConflictException("Error during upload: " + multipartFile.getOriginalFilename());
        }
    }

    public String createPath(MultipartFile multipartFile,String folder, String id, String name){

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String dateName = dateFormat.format(date);
        String fileName = id+"-"+name+"-" + dateName + "." + multipartFile.getContentType().split("/")[1];
        String finalPath = folder + fileName;

        return finalPath;
    }

    @Transactional
    public Foto actualizarFoto(Foto foto,String folderLocation, String id, String name, MultipartFile multipartFile){

        if (multipartFile.isEmpty()) {
            throw new FieldInvalidException("La imagen no puede ser nula");
        }

        String fileName = "src/main/resources/static"+foto.getRuta();

        Path path = Paths.get(fileName);
        File f = path.toFile();
        if (f.exists()) {
            f.delete();
        }

        String finalPath = createPath(multipartFile,folderLocation,id,name);
        String relativPath =finalPath.substring(25);

        try {
            byte[] bytes = multipartFile.getBytes();
            path = Paths.get(finalPath);
            Files.write(path, bytes);

            this.foto= foto;
            foto.setMime(multipartFile.getContentType());
            foto.setNombre(multipartFile.getName());
            foto.setRuta(relativPath);
            foto.setAlta(DISCHARGE);
            return fotoRepositorio.save(foto);
        }catch (Exception e) {
            e.printStackTrace();
            throw new ConflictException("Error during upload: " + multipartFile.getOriginalFilename());
        }
    }

    @Transactional(readOnly = true)
    public Foto obtenerFotoPorRuta(String ruta){
        return fotoRepositorio.findByRuta(ruta).orElseThrow(()-> new NoSuchElementException("No se encontró la imagen"));
    }

    @Transactional(readOnly = true)
    public byte[] obtenerFotoPorId(Long id){

        Foto foto = fotoRepositorio.getById(id);

        if(foto == null){
            throw new NotFoundException("La se halló ninguna imagen con el id '"+id+"'");
        }

        String ruta = foto.getRuta();

        try {
            String fileName = "src/main/resources/static"+ruta;
            Path path = Paths.get(fileName);
            File f = path.toFile();
            if (!f.exists()) {
                throw new ConflictException("La imagen no fue encontrada");
            }

            byte[] image = Files.readAllBytes(path);
            return image;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ConflictException("Error al mostrar imagen");
        }
    }
}
