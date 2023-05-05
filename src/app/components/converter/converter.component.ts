import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { General, Platforms } from 'src/app/models/constants/general';
import { ConvertedPlaylist } from 'src/app/models/converted-playlist';
import { Song } from 'src/app/models/song';
import { ConvertService } from 'src/app/services/convert/convert.service';

@Component({
  selector: 'app-converter',
  templateUrl: './converter.component.html',
  styleUrls: ['./converter.component.sass']
})
export class ConverterComponent implements OnInit{

  playlist: ConvertedPlaylist | undefined;
  convertedTo: string | null = localStorage.getItem(Platforms.CONVERTTO);
  error: boolean =false;
  
  constructor(private convertService: ConvertService, private router: Router){}

  ngOnInit(): void {
      this.playlist = this.convertService.getPlaylist();
      if (this.playlist != undefined) {
         console.log(this.playlist)
        //  this.convertService.setPlaylist
      }
      else{
        this.router.navigate(["/home"])
      }
     
  }

  removeSong(song: Song ){
    if (this.playlist) {
    
      if (this.playlist?.songList.length! > 0) {
        console.log(this.playlist);
        this.convertService.removeSongFromPlaylist(song, this.playlist.playlistID, this.playlist.snapshotID).subscribe((res)=>{

            this.playlist?.songList.forEach( (currentSong,index) =>{
              if (currentSong.id == song.id) {
                console.log("song removed")
                this.playlist?.songList.splice(index,1);              
              }
            })
            this.convertService.setPlaylist(this.playlist!);

        });
      }
      else{
        alert("There aren't any songs in the playlist. Convert a new playlist");
        this.router.navigate(["/home"])
      }
    }
    else{
      alert("Playlist was not converted. Try again")
      this.router.navigate(["/home"])
    }

    
  }

  
}
