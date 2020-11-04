package Capture;

import Utils.ConnectDatabase;
import Utils.PersonController;
import Utils.PersonModel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.bytedeco.javacpp.BytePointer;
import static org.bytedeco.opencv.global.opencv_core.CV_32SC1;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author Fred
 */
public class Capture extends javax.swing.JFrame {
//Criando uma Thread

    private Capture.DaemonThread myThread = null;
//Iniciando as variaveis
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("C://photos//haarcascade_frontalface_alt.xml");
    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector();

    String root, firstNamePerson, lastNamePerson, cargoPerson, dataPerson;
    int numSamples = 25, sample = 1, idPerson;
//Conectando a base de dados
    ConnectDatabase con = new ConnectDatabase();
//Construtor recebendo todos os parâmetros

    public Capture(int id, String fName, String lName, String cargo, String data) {
        initComponents();

        idPerson = id;
        firstNamePerson = fName;
        lastNamePerson = lName;
        cargoPerson = cargo;
        dataPerson = data;
//iniciando a camera
        startCamera();
    }

    private Capture() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        label_photo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        count_label = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Capture Photos");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CAPTURE 25 FOTOS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 470, 50));

        label_photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_photo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        jPanel1.add(label_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 440, 450));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        count_label.setBackground(new java.awt.Color(98, 141, 206));
        count_label.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        count_label.setForeground(new java.awt.Color(255, 255, 255));
        count_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        count_label.setText("00");
        count_label.setOpaque(true);
        jPanel2.add(count_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 100, 50));

        saveButton.setBackground(new java.awt.Color(106, 171, 128));
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("Capturar");
        saveButton.setContentAreaFilled(false);
        saveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveButton.setOpaque(true);
        jPanel2.add(saveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 100, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 440, 120));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 690));

        setSize(new java.awt.Dimension(488, 726));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Capture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Capture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Capture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Capture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Capture().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel count_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label_photo;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
//classe que cria a thread
    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        //pegando a imagem da camera
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);
                            Graphics g = label_photo.getGraphics(); //mostra a imagem no jlabel

                            Mat imageColor = new Mat(); //imagem colorida
                            imageColor = cameraImage;

                            Mat imageGray = new Mat(); //imagem pb
                            cvtColor(imageColor, imageGray, COLOR_BGRA2GRAY);

                            RectVector detectedFaces = new RectVector(); //face detectada
                            cascade.detectMultiScale(imageColor, detectedFaces, 1.1, 1, 1, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFaces.size(); i++) { //repetição pra encontrar as faces
                                Rect dadosFace = detectedFaces.get(0);

                                rectangle(imageColor, dadosFace, new Scalar(255, 255, 0, 2), 3, 0, 0);

                                Mat face = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(face, face, new Size(160, 160));

                                if (saveButton.getModel().isPressed()) { //quando apertar o botão saveButton

                                    if (sample <= numSamples) {
//                                        salva a imagem cortada [160,160]
//                                        nome do arquivo: idpessoa + a contagem de fotos. ex: person.10(id).6(sexta foto).jpg
                                        String cropped = "C:\\photos\\person." + idPerson + "." + sample + ".jpg";
                                        imwrite(cropped, face);

                                        //alterando o valor do contador antes do /25  
                                        count_label.setText(String.valueOf(sample) + "/25");
                                        //iterando
                                        sample++;
                                    }
                                    //se tiver mais de 25 fotos...
                                    if (sample > 25) {
                                        //chame o metodo generate
                                        generate();
                                        insertDatabase(); //insere os dados no banco
                                        //imprime que as fotos foram salvas
                                        System.out.println("File Generated");
                                        stopCamera(); // e fecha a camera
                                    }

                                }
                            }

                            imencode(".bmp", cameraImage, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                            BufferedImage buff = (BufferedImage) im;
                            try {
                                if (g.drawImage(buff, 0, 0, 360, 390, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                    if (runnable == false) {
                                        System.out.println("Salve a Foto");
                                        this.wait();
                                    }
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Ocorreu um erro!");
                            }
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao capturar!");
                    }
                }
            }
        }
    }

    public void generate() {
        //pegando o diretorio onde as fotos ficarão salvas
        File directory = new File("C://photos//");
        FilenameFilter filter = new FilenameFilter() {
            //filtrando as fotos
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg") || name.endsWith(".png");
            }
        };

        File[] files = directory.listFiles(filter);
        MatVector photos = new MatVector(files.length);
        Mat labels = new Mat(files.length, 1, CV_32SC1);
        IntBuffer labelsBuffer = labels.createBuffer();

        int counter = 0;
        //para cada imagem...
        for (File image : files) {
            Mat photo;
            //deixe a foto cinza
            photo = imread(image.getAbsolutePath(), Imgcodecs.IMREAD_GRAYSCALE);
            int idP = Integer.parseInt(image.getName().split("\\.")[1]);
            //faça um resize e deixe ela 160,160
            opencv_imgproc.resize(photo, photo, new Size(160, 160));

            photos.put(counter, photo);
            labelsBuffer.put(counter, idP);
            counter++;
        }
//pegando o reconhecedor de faces
        FaceRecognizer lbph = LBPHFaceRecognizer.create();
        lbph.train(photos, labels);
        //salve ele no arquivo yml
        lbph.save("C://photos//classifierLBPH.yml");
    }

    //salvando no banco
    public void insertDatabase() {
        PersonController cod = new PersonController();
        PersonModel mod = new PersonModel();

        mod.setFirst_name(firstNamePerson);
        mod.setLast_name(lastNamePerson);
        mod.setData(dataPerson);
        mod.setCargo(cargoPerson);

        cod.insert(mod);

    }
//desligando a camera
    public void stopCamera() {
        //desligando a thread
        myThread.runnable = false;
        //liberando a camera
        webSource.release();
        //fechando a janela
        dispose();
    }

    //ligando a camera
    public void startCamera() {
        //pegando a camera
        webSource = new VideoCapture(0);
        //iniciando a thread
        myThread = new Capture.DaemonThread();
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
    }
}
