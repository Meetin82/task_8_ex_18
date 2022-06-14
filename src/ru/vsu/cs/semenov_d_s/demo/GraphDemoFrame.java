package ru.vsu.cs.semenov_d_s.demo;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.*;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;
import ru.vsu.cs.semenov_d_s.Graph;
import ru.vsu.cs.semenov_d_s.utils.SwingUtils;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.StringReader;
import java.util.Locale;

public class GraphDemoFrame extends JFrame {
    private JTabbedPane tabbedPaneMain;
    private JPanel panelGraphTab;
    private JSplitPane splitPaneGraphTab1;
    private JTextArea textAreaSystemOut;
    private JSplitPane splitPaneGraphTab2;
    private JTextArea textAreaGraphFile;
    private JButton buttonCreateGraph;
    private JPanel panelGraphPainterContainer;
    private JPanel panelGraphvizTab;
    private JSplitPane splitPaneGraphvizTab1;
    private JPanel panelDotPainterContainer;
    private JTextArea textAreaDotFile;
    private JButton buttonDotPaint;
    private JPanel panelMain;
    private JLabel labelAddEdge;
    private JLabel labelAddVertex;
    private JTextField textFieldForNewVertex;
    private JButton buttonAddNewVertex;
    private JLabel labelV1;
    private JTextField textFieldForV1;
    private JLabel labelV2;
    private JTextField textFieldForV2;
    private JButton buttonAddEdge;
    private JButton buttonRemoveEdge;
    private JButton buttonRemoveVertex;
    private JLabel labelColorOfVertex;
    private JTextField textFieldForVertexColor;
    private JButton buttonIsBigraph;

    private SvgPanel panelGraphPainter;
    private SvgPanel panelGraphvizPainter;

    private Graph graph = null;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneMain = new JTabbedPane();
        tabbedPaneMain.setName("");
        panelMain.add(tabbedPaneMain, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        panelGraphTab = new JPanel();
        panelGraphTab.setLayout(new GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), 10, 10));
        tabbedPaneMain.addTab("Граф", null, panelGraphTab, "Демонстрация работы с графами");
        splitPaneGraphTab1 = new JSplitPane();
        splitPaneGraphTab1.setOrientation(0);
        splitPaneGraphTab1.setResizeWeight(0.75);
        panelGraphTab.add(splitPaneGraphTab1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        splitPaneGraphTab1.setRightComponent(scrollPane1);
        textAreaSystemOut = new JTextArea();
        Font textAreaSystemOutFont = this.$$$getFont$$$(null, -1, 22, textAreaSystemOut.getFont());
        if (textAreaSystemOutFont != null) textAreaSystemOut.setFont(textAreaSystemOutFont);
        scrollPane1.setViewportView(textAreaSystemOut);
        splitPaneGraphTab2 = new JSplitPane();
        splitPaneGraphTab2.setResizeWeight(0.0);
        splitPaneGraphTab1.setLeftComponent(splitPaneGraphTab2);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(7, 3, new Insets(0, 0, 0, 0), -1, -1));
        Font panel1Font = this.$$$getFont$$$(null, -1, 26, panel1.getFont());
        if (panel1Font != null) panel1.setFont(panel1Font);
        splitPaneGraphTab2.setLeftComponent(panel1);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel1.add(scrollPane2, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        textAreaGraphFile = new JTextArea();
        textAreaGraphFile.setText("a b (red) (green)\nb c (green) (red)\nc d (red) (green)\nc e (red) (green)");
        scrollPane2.setViewportView(textAreaGraphFile);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonCreateGraph = new JButton();
        buttonCreateGraph.setText("Построить граф");
        panel2.add(buttonCreateGraph, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel3, new GridConstraints(5, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelAddEdge = new JLabel();
        Font labelAddEdgeFont = this.$$$getFont$$$(null, -1, 14, labelAddEdge.getFont());
        if (labelAddEdgeFont != null) labelAddEdge.setFont(labelAddEdgeFont);
        labelAddEdge.setText("Создать ребро из двух имеющихся вершин");
        panel3.add(labelAddEdge, new GridConstraints(0, 0, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelV1 = new JLabel();
        labelV1.setText("v1");
        panel3.add(labelV1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textFieldForV1 = new JTextField();
        panel3.add(textFieldForV1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(43, 30), new Dimension(30, -1), 0, false));
        labelV2 = new JLabel();
        labelV2.setText("v2");
        panel3.add(labelV2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textFieldForV2 = new JTextField();
        panel3.add(textFieldForV2, new GridConstraints(1, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(30, 30), new Dimension(30, -1), 0, false));
        buttonAddEdge = new JButton();
        buttonAddEdge.setText("Добавить");
        panel3.add(buttonAddEdge, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonRemoveEdge = new JButton();
        buttonRemoveEdge.setText("Удалить");
        panel3.add(buttonRemoveEdge, new GridConstraints(2, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelAddVertex = new JLabel();
        Font labelAddVertexFont = this.$$$getFont$$$(null, -1, 14, labelAddVertex.getFont());
        if (labelAddVertexFont != null) labelAddVertex.setFont(labelAddVertexFont);
        labelAddVertex.setText("Вершина");
        panel1.add(labelAddVertex, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 7, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel4, new GridConstraints(6, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonIsBigraph = new JButton();
        buttonIsBigraph.setText("Проверить граф на двудолность");
        panel4.add(buttonIsBigraph, new GridConstraints(0, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textFieldForNewVertex = new JTextField();
        panel1.add(textFieldForNewVertex, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(30, 30), new Dimension(30, -1), 0, false));
        labelColorOfVertex = new JLabel();
        Font labelColorOfVertexFont = this.$$$getFont$$$(null, -1, 14, labelColorOfVertex.getFont());
        if (labelColorOfVertexFont != null) labelColorOfVertex.setFont(labelColorOfVertexFont);
        labelColorOfVertex.setText("Цвет");
        panel1.add(labelColorOfVertex, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textFieldForVertexColor = new JTextField();
        panel1.add(textFieldForVertexColor, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(30, 30), new Dimension(30, 30), 0, false));
        buttonAddNewVertex = new JButton();
        buttonAddNewVertex.setText("Добавить");
        panel1.add(buttonAddNewVertex, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonRemoveVertex = new JButton();
        buttonRemoveVertex.setText("Удалить");
        panel1.add(buttonRemoveVertex, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        splitPaneGraphTab2.setRightComponent(panel5);
        panelGraphPainterContainer = new JPanel();
        panelGraphPainterContainer.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.add(panelGraphPainterContainer, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelGraphvizTab = new JPanel();
        panelGraphvizTab.setLayout(new GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), 10, 10));
        tabbedPaneMain.addTab("Graphviz", null, panelGraphvizTab, "Демонстрация возможностей GraphViz");
        splitPaneGraphvizTab1 = new JSplitPane();
        splitPaneGraphvizTab1.setResizeWeight(0.0);
        panelGraphvizTab.add(splitPaneGraphvizTab1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        splitPaneGraphvizTab1.setRightComponent(panel6);
        panelDotPainterContainer = new JPanel();
        panelDotPainterContainer.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel6.add(panelDotPainterContainer, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        splitPaneGraphvizTab1.setLeftComponent(panel7);
        final JScrollPane scrollPane3 = new JScrollPane();
        panel7.add(scrollPane3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        textAreaDotFile = new JTextArea();
        textAreaDotFile.setText("graph {\n    2 -- { 3 -- 4 }\n    3 -- 6 -- { 2 4 }\n    7 -- 8 -- 7\n    9\n}\n");
        scrollPane3.setViewportView(textAreaDotFile);
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel7.add(panel8, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel8.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel7.add(panel9, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonDotPaint = new JButton();
        buttonDotPaint.setText("Отобразить");
        panel9.add(buttonDotPaint, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel9.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

    private static class SvgPanel extends JPanel {
        private String svg = null;
        private GraphicsNode svgGraphicsNode = null;

        public void paint(String svg) throws IOException {
            String xmlParser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory df = new SAXSVGDocumentFactory(xmlParser);
            SVGDocument doc = df.createSVGDocument(null, new StringReader(svg));
            UserAgent userAgent = new UserAgentAdapter();
            DocumentLoader loader = new DocumentLoader(userAgent);
            BridgeContext ctx = new BridgeContext(userAgent, loader);
            ctx.setDynamicState(BridgeContext.DYNAMIC);
            GVTBuilder builder = new GVTBuilder();
            svgGraphicsNode = builder.build(ctx, doc);

            this.svg = svg;
            repaint();
        }

        @Override
        public void paintComponent(Graphics gr) {
            super.paintComponent(gr);

            if (svgGraphicsNode == null) {
                return;
            }

            double scaleX = this.getWidth() / svgGraphicsNode.getPrimitiveBounds().getWidth();
            double scaleY = this.getHeight() / svgGraphicsNode.getPrimitiveBounds().getHeight();
            double scale = Math.min(scaleX, scaleY);
            AffineTransform transform = new AffineTransform(scale, 0, 0, scale, 0, 0);
            svgGraphicsNode.setTransform(transform);
            Graphics2D g2d = (Graphics2D) gr;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            svgGraphicsNode.paint(g2d);
        }
    }

    public GraphDemoFrame() {
        this.setTitle("Графы");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        splitPaneGraphTab1.setBorder(null);
        splitPaneGraphTab2.setBorder(null);
        splitPaneGraphvizTab1.setBorder(null);

        panelGraphPainterContainer.setLayout(new BorderLayout());
        panelGraphPainter = new SvgPanel();
        panelGraphPainterContainer.add(new JScrollPane(panelGraphPainter));

        panelDotPainterContainer.setLayout(new BorderLayout());
        panelGraphvizPainter = new SvgPanel();
        panelDotPainterContainer.add(new JScrollPane(panelGraphvizPainter));

        buttonCreateGraph.addActionListener(e -> {
            try {
                graph = Graph.graphFromString(textAreaGraphFile.getText());
                panelGraphPainter.paint(dotToSvg(graph.toDot()));
            } catch (Exception exc) {
                SwingUtils.showErrorMessageBox(exc);
            }
        });

        buttonAddNewVertex.addActionListener(e -> {
            try {
                graph.addVertex(textFieldForNewVertex.getText(), Graph.colorFromString(textFieldForVertexColor.getText()));
                panelGraphPainter.paint(dotToSvg(graph.toDot()));
            } catch (Exception exc) {
                SwingUtils.showErrorMessageBox(exc);
            }
        });

        buttonDotPaint.addActionListener(e -> {
            try {
                panelGraphvizPainter.paint(dotToSvg(textAreaDotFile.getText()));
            } catch (Exception exc) {
                SwingUtils.showErrorMessageBox(exc);
            }
        });

        buttonAddEdge.addActionListener(e -> {
            try {
                graph.addEdge(textFieldForV1.getText(), textFieldForV2.getText());
                panelGraphPainter.paint(dotToSvg(graph.toDot()));
            } catch (Exception exc) {
                SwingUtils.showErrorMessageBox(exc);
            }
        });

        buttonRemoveVertex.addActionListener(e -> {
            try {
                graph.removeVertex(textFieldForNewVertex.getText());
                panelGraphPainter.paint(dotToSvg(graph.toDot()));
            } catch (Exception exc) {
                SwingUtils.showErrorMessageBox(exc);
            }
        });

        buttonRemoveEdge.addActionListener(e -> {
            try {
                graph.removeEdge(textFieldForV1.getText(), textFieldForV2.getText());
                panelGraphPainter.paint(dotToSvg(graph.toDot()));
            } catch (Exception exc) {
                SwingUtils.showErrorMessageBox(exc);
            }
        });

        buttonIsBigraph.addActionListener(e -> {
            String result;
            if (graph.isBigraph()) {
                result = "Данный граф является двудольным!";
            } else {
                result = "Данный граф не является двудольным!";
            }
            textAreaSystemOut.setText(result);
        });
    }

    private static String dotToSvg(String dotSrc) throws IOException {
        MutableGraph g = new Parser().read(dotSrc);
        return Graphviz.fromGraph(g).render(Format.SVG).toString();
    }
}
