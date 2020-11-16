package ca.wanwari;

/*
 * TransferableImage.java
 * Author: https://stackoverflow.com/a/29516898
 * Implements the interface for Transferable so the data (BufferedImage) can be used in a transfer operation
 * Refer to the Transferable and DataFlavor APIs
 * https://docs.oracle.com/en/java/javase/15/docs/api/java.datatransfer/java/awt/datatransfer/Transferable.html
 * https://docs.oracle.com/en/java/javase/15/docs/api/java.datatransfer/java/awt/datatransfer/DataFlavor.html
 */

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;

class TransferableImage implements Transferable {

    BufferedImage imageToTransfer;

    /*
        TransferableImage(BufferedImage imageToTransfer)
        sets the BufferedImage within the object to the BufferedImage received
     */
    TransferableImage(BufferedImage imageToTransfer) {
        this.imageToTransfer = imageToTransfer;
    }

    /*
        public DataFlavor[] getTransferDataFlavors()
        'Returns an array of DataFlavor objects indicating the flavors the data can be provided in'
        object contains imageFlavor as the chosen DataFlavor
        DataFlavor.imageFlavor represents a Java Image class
     */
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] { DataFlavor.imageFlavor };
    }

    /*
        public boolean isDataFlavorSupported(DataFlavor flavor)
        'Returns whether or not the specified data flavor is supported for this object'
        returns whether or not the DataFlavor passed is equal to an imageFlavor
     */
    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor == DataFlavor.imageFlavor;
    }

    /*
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException
        'Returns an object which represents the data to be transferred. The class of the object returned is defined by
         the representation class of the flavor'
         if isDataFlavorSupported returns true return the BufferedImage
         else throw an UnsupportedFlavorException
     */
    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (isDataFlavorSupported(flavor)) {
            return imageToTransfer;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}