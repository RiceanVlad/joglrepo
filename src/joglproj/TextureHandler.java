package joglproj;

import java.io.IOException;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;

public class TextureHandler {
	
	GL gl;
	GLU glu;
	String filename;
	boolean mipmapped;
	TextureReader.Texture texreader;
	
	public void bind(GL gl,int tex)
	{
		gl.glBindTexture(GL.GL_TEXTURE_2D, tex);
	}
	public void enable(GL gl)
	{
		 gl.glEnable(GL.GL_TEXTURE_2D);
	}
	public void disable(GL gl)
	{
		gl.glDisable(GL.GL_TEXTURE_2D);
	}
	TextureHandler()
	{
		this.gl=null;
		this.glu=null;
		this.filename=null;
		this.mipmapped=false;
		
	}
	TextureHandler(GL gl,GLU glu,String filename,boolean mip)
	{
		this.gl=gl;
		this.glu=glu;
		this.filename=filename;
		this.mipmapped=mip;
		this.texreader=getTexture(filename);
	}
	public TextureReader.Texture getTexture(String file)
	{
		TextureReader.Texture tex;
		try {
			tex = TextureReader.readTexture(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return tex;
	}
	public void configureTexture()
	{
		 makeRGBTexture(this.gl,this.glu,this.texreader,GL.GL_TEXTURE_2D,this.mipmapped);
	}
	
	private void makeRGBTexture(GL gl, GLU glu, TextureReader.Texture img, int target, boolean mipmapped) 
	{     
        if (mipmapped) 
        {
        	glu.gluBuild2DMipmaps(target, GL.GL_RGB8, img.getWidth(), img.getHeight(), GL.GL_RGB, GL.GL_UNSIGNED_BYTE, img.getPixels());
		} 
        else 
        {
			gl.glTexImage2D(target, 0, GL.GL_RGB, img.getWidth(), img.getHeight(), 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, img.getPixels());
		}
	}
}